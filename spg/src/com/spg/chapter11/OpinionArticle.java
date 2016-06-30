package com.spg.chapter11;

import java.util.ArrayList;
import java.util.List;

public class OpinionArticle implements Article {
	private String title;
	private String articleText;
	
	private List<Comment> comments;

	
	@Override
	public String getTitle() {
		return title;
	}

	
	@Override
	public void setTitle( String title ) {
		this.title = title;
	}

	
	@Override
	public String getArticleText() {
		return articleText;
	}

	
	@Override
	public void setArticleText( String articleText ) {
		this.articleText = articleText;
	}

	@Override
	public List<Comment> getComments() {
		return comments;
	}
	
	@Override
	public Comment getComment( int commentId ) {
		if ( comments == null ) {
			comments = new ArrayList<>();
		}

		return comments.get( commentId );
	}

	
	@Override
	public void addComment( String author, String text ) {
		if ( comments == null ) {
			comments = new ArrayList<>();
		}

		this.comments.add( new UserComment( author, text ) );
	}
	
}
