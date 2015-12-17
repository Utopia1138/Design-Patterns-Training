package org.axp.observer.post;

/**
 * Similar to an RSS feed, this gives us a common interface for various posts, whatever their original source.
 * 
 * @author axp
 */
public interface Post {
	public String getAuthor();
	public String getTitle();
	public String getSummary();
	public String getFullText();
	public String getPermanentUrl();
}