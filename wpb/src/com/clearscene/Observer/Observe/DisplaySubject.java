package com.clearscene.Observer.Observe;


public interface DisplaySubject {

	public void registerDisplayer( DisplayObserver o );
	
	public void deregisterDisplayer( DisplayObserver o );

	public void notifyDisplayers();
	
}
