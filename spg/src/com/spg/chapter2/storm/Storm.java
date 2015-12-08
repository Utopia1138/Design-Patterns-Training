package com.spg.chapter2.storm;

import com.spg.chapter2.watcher.Watcher;

public interface Storm {

	public void registerWatcher( Watcher watcher );
	public void removeWatcher( Watcher watcher );
	public void notifyWatchers();
}
