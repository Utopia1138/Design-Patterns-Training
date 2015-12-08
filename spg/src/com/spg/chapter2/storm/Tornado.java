package com.spg.chapter2.storm;

import java.util.ArrayList;
import java.util.List;

import com.spg.chapter2.Coords;
import com.spg.chapter2.watcher.Watcher;

public class Tornado implements Storm {

	private List<Watcher> watcherList; 
	
	private Coords location;
	
	public Tornado () {
		watcherList = new ArrayList<Watcher>();
		
		// Tornado spawns somewhere random in a 5x5 kilometre area
		location = new Coords( (int) Math.floor( ( Math.random() * 5000 ) ), (int) Math.floor( ( Math.random() * 5000 ) ), 5000 );
	}
	
	@Override
	public void registerWatcher( Watcher watcher ) {
		watcherList.add( watcher );
	}

	@Override
	public void removeWatcher( Watcher watcher ) {
		int index = watcherList.indexOf( watcher );
		
		if ( index >= 0 ) {
			watcherList.remove( index );
		}
	}

	@Override
	public void notifyWatchers() {
		for ( Watcher watcher: watcherList ) {
			watcher.watch( location );
		}
	}
	
	public void move() {
		// Suddenly tornado moves up to a kilometre in any direction (within the area)
		location.setXCoord( location.getXCoord() + (int) Math.floor( ( ( Math.random() - 0.5 ) * 2000 ) ) ); 
		location.setYCoord( location.getYCoord() + (int) Math.floor( ( ( Math.random() - 0.5 ) * 2000 ) ) ); 

		System.out.println( "---------------------------------------------------------" );
		System.out.println( "The tornado has moved to x: " + location.getXCoord() + " y: " + location.getYCoord() );
		
		notifyWatchers();
	}

}
