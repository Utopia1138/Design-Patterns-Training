package org.axp.observer.display;

import java.io.PrintStream;
import java.util.Observable;
import java.util.Observer;

import org.axp.observer.post.Post;

/**
 * An abstract device for displaying posts.
 * 
 * @author axp
 */
public abstract class AbstractDisplay implements Observer {
	protected PrintStream out;
	
	public AbstractDisplay() {
		setPrintStream( System.out );
	}
	
	public void setPrintStream( PrintStream out ) {
		this.out = out;
	}

	public void update( Observable o, Object arg ) {
		if ( arg == null ) {
			throw new IllegalArgumentException( "Got a null Post" );
		}
		if ( !( arg instanceof Post ) ) {
			throw new IllegalArgumentException( "Can only display Post objects, not " + arg.getClass().getName() );
		}
		
		show( (Post) arg );
	}
	
	public abstract void show( Post p );
}
