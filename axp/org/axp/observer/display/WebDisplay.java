package org.axp.observer.display;

import org.axp.observer.post.Post;

public class WebDisplay extends AbstractDisplay {
	@Override
	public void show( Post p ) {
		out.println( "<div class='post'>" );
		out.print( "<h3>" + p.getAuthor() );
		
		if ( p.getTitle() != null ) {
			out.println( ": <a href='" + p.getPermanentUrl() + "'>" + p.getTitle() +"</a></h3>" );
		}
		else {
			out.println( " <a href='" + p.getPermanentUrl() + "'>writes</a>:</h3>" );
		}
		
		out.println( "<p>" + p.getSummary() + "</p>" );
		out.println( "</div>" );
	}
}
