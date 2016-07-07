
package com.spg.chapter11;

/**
 * POJO representing a comment on an article on a website
 *
 */
public interface Comment {

	public String getCommentText();

	public void setCommentText( String commentText );

	public String getCommentAuthor();

	public void setCommentAuthor( String commentAuthor );

	public int getScore();

	public void changeScore( int change );

	public boolean isFlagged();

	public void setFlagged( boolean flagged );

}
