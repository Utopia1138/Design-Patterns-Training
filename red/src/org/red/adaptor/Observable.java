package org.red.adaptor;

import org.red.observer.Observer;

public interface Observable<T> {
	Observable<T> bind(Observer<T> observer);
	Observable<T> unbind(Observer<T> observer);
	void update(T event);
}