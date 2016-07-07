package com.spg.chapter11.users;

import java.lang.reflect.InvocationHandler;

import com.spg.chapter11.Article;
import com.spg.chapter11.Comment;
import com.spg.chapter11.handler.ArticleReaderHandler;
import com.spg.chapter11.handler.CommentModeratorHandler;


public class ModeratorAccount implements UserAccount {

	private String name;
	
	public ModeratorAccount( String name ) {
		this.name = name;
	}

	@Override
	public InvocationHandler getArticleHandler( Article article ) {
		return new ArticleReaderHandler( article );
	}

	@Override
	public InvocationHandler getCommentHandler( Comment comment ) {
		return new CommentModeratorHandler( comment );
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName( String name ) {
		this.name = name;		
	}

}
