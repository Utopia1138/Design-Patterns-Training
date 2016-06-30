package com.spg.chapter11.handler;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.spg.chapter11.Article;

/**
 * Article reader can just read the article and add comments.
 *
 */
public class ArticleReaderHandler implements InvocationHandler {

	private Article article;

	public ArticleReaderHandler( Article article ) {
		super();
		this.article = article;
	}

	@Override
	public Object invoke( Object proxy, Method method, Object[] args ) throws IllegalAccessException {
		try {
			if ( method.getName().startsWith( "get" ) ) {
				method.invoke( article, args );
			}
			else if ( method.getName().startsWith( "set" ) ) {
				throw new IllegalAccessException( "Readers cannot change article contents" );
			}
			else if ( method.getName().startsWith( "add" ) ) {
				method.invoke( article, args );
			}
		}
		catch ( InvocationTargetException e ) {
			throw new IllegalAccessException( "Error when invoking method" );
		}
		
		return null;
	}

}
