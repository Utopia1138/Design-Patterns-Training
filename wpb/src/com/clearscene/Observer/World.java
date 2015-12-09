package com.clearscene.Observer;

import java.util.ArrayList;

import com.clearscene.Observer.Cells.CommonCell;
import com.clearscene.Observer.Observe.DisplayObserver;
import com.clearscene.Observer.Observe.WorldObserver;

public class World {

	ArrayList<WorldObserver> Plots = new ArrayList();
	int worldSizex = 60;
	int worldSizey = 20;
	Display display;
	
	
	//
	// Create a game space
	//
	public void initialise() {
		
		// Create world
		display = new Display( worldSizex, worldSizey );
		Plot[][] world = new Plot[worldSizex+1][worldSizey+1];

		for( int x = 0; x <= worldSizex ; x++ ) {
			for( int y = 0; y <= worldSizey ; y++ ) {
				world[x][y] = new Plot( x, y );
				world[x][y].registerDisplayer( display );
				Plots.add( world[x][y] );
			}
		}
		
		// Populate with cells
		for( int x = 2; x <= worldSizex-2 ; x++ ) {
			for( int y = 2; y <= worldSizey-2 ; y++ ) {
				world[x][y].infestWithCell( new CommonCell() );
				world[x][y].notifyDisplayers();
			}
		}
		
		// Setup relationships
		for( int x = 2; x <= worldSizex-2 ; x++ ) {
			for( int y = 2; y <= worldSizey-2 ; y++ ) {
				world[x][y].askToKeepUp2DateAboutSomeCell( world[x+1][y-1].getCell() );
				world[x][y].askToKeepUp2DateAboutSomeCell( world[x+1][y].getCell() );
				world[x][y].askToKeepUp2DateAboutSomeCell( world[x+1][y+1].getCell() );
				world[x][y].askToKeepUp2DateAboutSomeCell( world[x-1][y-1].getCell() );
				world[x][y].askToKeepUp2DateAboutSomeCell( world[x-1][y].getCell() );
				world[x][y].askToKeepUp2DateAboutSomeCell( world[x-1][y+1].getCell() );
				world[x][y].askToKeepUp2DateAboutSomeCell( world[x][y-1].getCell() );
				world[x][y].askToKeepUp2DateAboutSomeCell( world[x][y+1].getCell() );
			}
		}
		
		// Kill some off - to create some interest
		world[15][8].killCell();
		world[15][9].killCell();
		world[15][10].killCell();
		world[16][8].killCell();
		world[16][9].killCell();
		world[16][10].killCell();
		world[17][8].killCell();
		world[17][9].killCell();
		world[17][10].killCell();
		world[18][8].killCell();
		world[18][9].killCell();
		world[18][10].killCell();
	}
	
	
	//
	// Run a single game turn
	//
	public void play() {
		
		Boolean stillPlay = false;

	  for( WorldObserver plot : Plots ) {

				if ( ((Plot)plot).getState() == Plot.state.ALIVE ) {
					stillPlay = true;
					((Plot)plot).ravage();
				}
				
				if ( ((Plot)plot).getState() != Plot.state.ALIVE ) {
					((Plot)plot).notifyDisplayers();
				}
				
		}
		
		if( ! stillPlay ) {
			System.exit( 1 );
		}

	}


}
