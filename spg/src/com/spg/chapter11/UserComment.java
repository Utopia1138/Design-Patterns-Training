package com.spg.chapter11;


public class UserComment implements Comment {

	private String commentText;
	private String commentAuthor;
	
	private int score;
	private boolean flagged;
	
	public UserComment( String author, String text ) {
		this.commentAuthor = author;
		this.commentText = text;
		this.score = 0;
		this.flagged = false;
	}

	@Override
	public String getCommentText() {
		return commentText;
	}
	
	@Override
	public void setCommentText( String commentText ) {
		this.commentText = commentText;
	}
	
	@Override
	public String getCommentAuthor() {
		return commentAuthor;
	}
	
	@Override
	public void setCommentAuthor( String commentAuthor ) {
		this.commentAuthor = commentAuthor;
	}
	
	@Override
	public int getScore() {
		return score;
	}
	
	@Override
	public void changeScore( int change ) {
		this.score += change;
	}
	
	@Override
	public boolean isFlagged() {
		return flagged;
	}
	
	@Override
	public void setFlagged( boolean flagged ) {
		this.flagged = flagged;
	}

}
