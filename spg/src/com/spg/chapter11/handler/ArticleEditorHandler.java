
package com.spg.chapter11.handler;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.spg.chapter11.Article;

/**
 * Article editor can change anything about the article.
 *
 */
public class ArticleEditorHandler implements InvocationHandler {

	private Article article;

	public ArticleEditorHandler( Article article ) {
		super();
		this.article = article;
	}

	@Override
	public Object invoke( Object proxy, Method method, Object[] args ) throws IllegalAccessException {

		try {
			if ( method.getName().startsWith( "get" ) ) {
				return method.invoke( article, args );
			}
			else if ( method.getName().startsWith( "set" ) ) {
				return method.invoke( article, args );
			}
			else if ( method.getName().startsWith( "add" ) ) {
				return method.invoke( article, args );
			}
		}
		catch ( InvocationTargetException e ) {
			throw new IllegalAccessException( "Error when invoking method" );
		}

		return null;
	}

}
