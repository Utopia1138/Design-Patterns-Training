
package com.spg.chapter11;

import java.util.List;

/**
 * POJO representing an article on a website
 *
 */
public interface Article {

	public String getTitle();

	public void setTitle( String title );

	public String getArticleText();

	public void setArticleText( String articleText );

	public List<Comment> getComments();

	public Comment getComment( int commentId );

	public void addComment( String author, String text );

}
