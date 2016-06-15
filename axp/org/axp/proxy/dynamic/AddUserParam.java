package org.axp.proxy.dynamic;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Optional annotation denoting that rather than executing the standard version of a method, the invocation
 * handler should execute a method with an additional {@link User} as the final parameter
 */
@Target( ElementType.METHOD )
@Retention( RetentionPolicy.RUNTIME )
public @interface AddUserParam {

}
