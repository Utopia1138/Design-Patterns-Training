
package com.spg.chapter11.handler;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.spg.chapter11.Comment;

/**
 * Comment moderator can make comments and edit existing comments, but can't change the score or
 * status of comments
 *
 */
public class CommentModeratorHandler implements InvocationHandler {

	private Comment comment;

	public CommentModeratorHandler( Comment comment ) {
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
				throw new IllegalAccessException( "Moderator cannot flag comment" );
			}
			else if ( method.getName().startsWith( "set" ) ) {
				return method.invoke( comment, args );
			}
			else if ( method.getName().startsWith( "change" ) ) {
				throw new IllegalAccessException( "Moderator cannot change comment score" );
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
