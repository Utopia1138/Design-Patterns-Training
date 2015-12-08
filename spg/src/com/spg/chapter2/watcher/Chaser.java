package com.spg.chapter2.watcher;

import com.spg.chapter2.Coords;

public class Chaser implements Watcher {

	private Coords location;
	
	public Chaser( int locationX, int locationY) {
		location = new Coords( locationX, locationY, 5000 );
	}
	
	@Override
	public void watch( Coords stormLocation ) {
				
		int distance = location.calculateDistance( stormLocation );
		
		if ( distance > 1000 ) {
			moveCloser( stormLocation );
		} else if ( distance < 1000 && distance > 200 ) {
			watchInAwe();
		}
		else if ( distance < 200 && distance > 10 ) {
			welp();
		}
		else {
			eyeOfTheStorm();
		}
		
	}

	private void moveCloser( Coords stormLocation ) {
		System.out.println( "Move closer! I want to get a better view!" );
		
		location.setXCoord( stormLocation.getXCoord() > location.getXCoord() ? location.getXCoord() + 500 : location.getXCoord() - 500 );
		location.setYCoord( stormLocation.getYCoord() > location.getYCoord() ? location.getYCoord() + 500 : location.getYCoord() - 500 );
	}
	
	private static void watchInAwe() {
		System.out.println( "It's beautiful! Nothing could spoil this moment!" );
	}
	
	private static void welp() {
		System.out.println( "Oh the hubris!" );
		System.out.println( "*dies*" );
	}
	
	private static void eyeOfTheStorm() {
		System.out.println( "Good thing we found this convienent pipe to tie ourselves to!" );
	}

}
