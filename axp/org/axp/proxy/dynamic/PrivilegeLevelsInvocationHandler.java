package org.axp.proxy.dynamic;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import org.apache.commons.lang3.ArrayUtils;
import org.axp.proxy.dynamic.changelog.SimpleChangeLog;

/**
 * An invocation handler which restricts access to the target's methods based on the privilege level of
 * a particular user, matching them against the {@link AccessCheck} annotations on the target interface.
 * 
 * This handler can also automatically add in the user as an extra parameter in certain method calls, if
 * that method is annotated with {@link AddUserParam}.
 *
 * @param <T> the interface which you wish the proxy to conform to.
 */
public class PrivilegeLevelsInvocationHandler<T> implements InvocationHandler {
	private T invocationTarget;
	private User user;
	
	/**
	 * Create a new invocation handler. Note that instead of calling this directly, you'll
	 * usually want to call {@link #getProxy(Class, Object, User)}.
	 * 
	 * @param invocationTarget the unprotected class we want to invoke methods on
	 * @param user the user invoking the methods
	 */
	protected PrivilegeLevelsInvocationHandler( T invocationTarget, User user ) {
		this.invocationTarget = invocationTarget;
		this.user = user;
	}
	
	/*
	 * Invoke the correct method on the target, based on the annotations on the interface and the privilege
	 * level of the user.
	 */
	@Override
	public Object invoke( Object proxy, Method method, Object[] args ) throws Throwable {
		try {
			AccessCheck check = method.getAnnotation( AccessCheck.class );
			
			if ( check == null ) {
				throw new IllegalAccessException( "Unknown access level for method '#" + method.getName() + '\'' );
			}
			else if ( user.getPrivilegeLevel() < check.value() ) {
				throw new IllegalAccessException( "User's access level is too low for method '#" + method.getName() + '\'' );
			}
			
			if ( method.getAnnotation( AddUserParam.class ) != null ) {
				Class<?>[] paramTypes = ArrayUtils.add( method.getParameterTypes(), User.class );
				Object[] arguments = ArrayUtils.add( args, this.user );
				
				Method actualMethod = SimpleChangeLog.class.getMethod( method.getName(), paramTypes );
				return actualMethod.invoke( this.invocationTarget, arguments );
			}
			
			return method.invoke( this.invocationTarget, args );
		}
		catch ( InvocationTargetException e ) {
			throw e.getCause();
		}
	}
	
	/**
	 * Construct a new proxy to an invocation target
	 * 
	 * @param invocationTarget the unprotected class we want to invoke methods on
	 * @param user the user invoking the methods
	 * @return a proxy object which conforms to the same interface as the target, but only permits certain method calls
	 * based on the user's privilege level
	 */
	@SuppressWarnings("unchecked")
	public static <T> T getProxy( T invocationTarget, User user ) {
		return (T) Proxy.newProxyInstance( 
				invocationTarget.getClass().getClassLoader(),
				invocationTarget.getClass().getInterfaces(),
				new PrivilegeLevelsInvocationHandler<T>( invocationTarget, user ) );
	}
}
