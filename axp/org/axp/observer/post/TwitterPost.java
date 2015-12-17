package org.axp.observer.post;

/**
 * Twitter posts are short, so summaries are the same as full text, and they don't have titles.
 * 
 * @author axp
 */
public class TwitterPost implements Post {
	private String author;
	private String text;
	private String url;

	public TwitterPost( String author, String text, String url ) {
		this.author = author;
		this.text = text;
		this.url = url;
	}

	@Override
	public String getAuthor() {
		return this.author;
	}

	@Override
	public String getTitle() {
		return null;
	}

	@Override
	public String getSummary() {
		return this.text;
	}

	@Override
	public String getFullText() {
		return this.text;
	}

	@Override
	public String getPermanentUrl() {
		return this.url;
	}
}