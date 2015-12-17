package org.red.observer;

/**
 * Types which permit binding/unbinding on observers that await
 * updates on changes to the state of this type.
 *
 * @param <T> event type for notifications
 */
public interface Observable<T> {
	Observable<T> bind(Observer<T> observer);
	Observable<T> unbind(Observer<T> observer);
}
