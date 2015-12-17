package com.clearscene.Observer.Observe;

import com.clearscene.Observer.Plot;


public interface DisplayObserver {

	public void updateDisplay( int x, int y, Plot.state state, int heat );
	
}
