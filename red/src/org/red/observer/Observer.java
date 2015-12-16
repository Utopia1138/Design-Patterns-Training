package org.red.observer;

/**
 * Simple interface for observers that await updates from some
 * subject.
 *
 * @param <T> event type for push notification
 */
@FunctionalInterface
public interface Observer<T> {
	void onEvent( T event );
}
