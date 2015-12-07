package org.axp.observer;

import java.util.Observable;

import org.axp.observer.post.Post;

public class Feed<T extends Post> extends Observable {
	public void publish( T post ) {
		setChanged();
		super.notifyObservers( post );
	}
}