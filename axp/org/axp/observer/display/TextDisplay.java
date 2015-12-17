package org.axp.observer.display;

import org.axp.observer.post.Post;

public class TextDisplay extends AbstractDisplay {
	@Override
	public void show( Post p ) {
		if ( p.getTitle() != null ) {
			out.println( "= " + p.getTitle() + " =" );
		}
		
		out.println( p.getFullText() );
		out.println( "    -- " + p.getAuthor() );
		out.println( "---" );
	}
}