
package com.spg.chapter2;

import com.spg.chapter2.storm.Storm;
import com.spg.chapter2.storm.Tornado;
import com.spg.chapter2.watcher.Chaser;
import com.spg.chapter2.watcher.Farmer;
import com.spg.chapter2.watcher.Weatherman;

public class TornadoSeason {

	public static void main( String[] args ) throws Exception {

		Tornado categoryF5 = new Tornado();

		categoryF5.registerWatcher( new Chaser( 0, 0 ) );
		categoryF5.registerWatcher( new Weatherman( 5000, 5000 ) );
		categoryF5.registerWatcher( new Farmer( 2000, 500 ) );

		while ( true ) {
			categoryF5.move();
			Thread.sleep( 2000 );
		}
	}

}
