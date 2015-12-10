
package com.spg.chapter2.watcher;

import com.spg.chapter2.Coords;

public class Weatherman implements Watcher {

	private Coords	location;
	private int			fuel	= 100;

	public Weatherman( int locationX, int locationY ) {
		location = new Coords( locationX, locationY, 5000 );
	}

	@Override
	public boolean watch( Coords stormLocation ) {

		int distance = location.calculateDistance( stormLocation );

		if ( fuel == 0 ) {
			leave();
			return false;
		}

		if ( distance > 2000 ) {
			moveCloser( stormLocation );
		}
		else if ( distance < 2000 && distance > 1000 ) {
			report();
		}
		else if ( distance < 1000 && distance > 500 ) {
			moveFurther( stormLocation );
		}
		else {
			welp();
			return false;
		}

		fuel--;
		return true;
	}

	private void moveCloser( Coords stormLocation ) {
		System.out.println( "We can fly a little bit closer..." );

		location.setXCoord( stormLocation.getXCoord() > location.getXCoord() ? location.getXCoord() + 500 : location.getXCoord() - 500 );
		location.setYCoord( stormLocation.getYCoord() > location.getYCoord() ? location.getYCoord() + 500 : location.getYCoord() - 500 );
	}

	private void moveFurther( Coords stormLocation ) {
		System.out.println( "Woah! Too close!" );

		location.setXCoord( stormLocation.getXCoord() > location.getXCoord() ? location.getXCoord() - 500 : location.getXCoord() + 500 );
		location.setYCoord( stormLocation.getYCoord() > location.getYCoord() ? location.getYCoord() - 500 : location.getYCoord() + 500 );
	}

	private static void report() {
		System.out.println( "This is your eye in the sky, live from the scene of the storm!" );
	}

	private static void leave() {
		System.out.println( "This is your eye in the sky, signing off!" );
	}

	private static void welp() {
		System.out.println( "Oh the humanity!" );
		System.out.println( "*dies*" );
	}

}
