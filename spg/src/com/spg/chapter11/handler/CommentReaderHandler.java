
package com.spg.chapter11.handler;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.spg.chapter11.Comment;

/**
 * Comment reader can only read and rate comments, as well as flag them for moderation.
 *
 */
public class CommentReaderHandler implements InvocationHandler {

	private Comment comment;

	public CommentReaderHandler( Comment comment ) {
		super();
		this.comment = comment;
	}

	@Override
	public Object invoke( Object proxy, Method method, Object[] args ) throws IllegalAccessException {
		try {
			if ( method.getName().startsWith( "get" ) ) {
				return method.invoke( comment, args );
			}
			else if ( method.getName().startsWith( "setFlagged" ) ) {
				return method.invoke( comment, args );
			}
			else if ( method.getName().startsWith( "set" ) ) {
				throw new IllegalAccessException( "Reader cannot change comment contents" );
			}
			else if ( method.getName().startsWith( "change" ) ) {
				return method.invoke( comment, args );
			}
			else if ( method.getName().startsWith( "is" ) ) {
				return method.invoke( comment, args );
			}
		}
		catch ( InvocationTargetException e ) {
			throw new IllegalAccessException( "Error when invoking method" );
		}

		return null;
	}

}
