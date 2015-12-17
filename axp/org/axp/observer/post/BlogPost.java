package org.axp.observer.post;

/**
 * Represents a possibly longer post on someone's blog. Automatically generates summaries under 200 chars.
 * 
 * @author axp
 */
public class BlogPost implements Post {
	private String author;
	private String text;
	private String url;
	private String title;
	private String summary;

	public BlogPost( String author, String title, String text, String url ) {
		this.author = author;
		this.title = title;
		this.text = text;
		this.url = url;
		
		if ( text.length() > 200 ) {
			this.summary = text.substring( 0, 196 ) + " ...";
		}
		else {
			this.summary = text;
		}
	}

	@Override
	public String getAuthor() {
		return this.author;
	}

	@Override
	public String getTitle() {
		return this.title;
	}

	@Override
	public String getSummary() {
		return this.summary;
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
