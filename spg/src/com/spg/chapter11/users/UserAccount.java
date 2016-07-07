package com.spg.chapter11.users;

import java.lang.reflect.InvocationHandler;

import com.spg.chapter11.Article;
import com.spg.chapter11.Comment;

public interface UserAccount {

	public InvocationHandler getArticleHandler( Article article );
	public InvocationHandler getCommentHandler( Comment comment );
	
	public String getName();
	public void setName( String name );
	
}
