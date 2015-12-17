
package com.spg.chapter2.watcher;

import java.util.ArrayList;
import java.util.List;

import com.spg.chapter2.Coords;

public class Farmer implements Watcher {

	private Coords				houseLocation;
	private List<Coords>	fieldLocations;
	private String				location	= "porch";

	public Farmer( int locationX, int locationY ) {
		houseLocation = new Coords( locationX, locationY, 5000 );

		// Farmer owns a few fields scattered around the area
		fieldLocations = new ArrayList<Coords>();
		fieldLocations.add( new Coords( 1000, 2000, 5000 ) );
		fieldLocations.add( new Coords( 2500, 700, 5000 ) );
		fieldLocations.add( new Coords( 3000, 100, 5000 ) );
		fieldLocations.add( new Coords( 1800, 4300, 5000 ) );
		fieldLocations.add( new Coords( 4999, 4999, 5000 ) );
	}

	@Override
	public boolean watch( Coords stormLocation ) {

		int houseDistance = houseLocation.calculateDistance( stormLocation );

		if ( houseDistance > 800 ) {
			watchFromAfar( stormLocation );
		}
		else if ( houseDistance < 800 && houseDistance > 200 ) {
			getToTheCellar();
		}
		else {
			if ( location.equals( "cellar" ) ) {
				rideItOut();
			}
			else {
				welp();
				return false;
			}
		}
		return true;

	}

	private void watchFromAfar( Coords stormLocation ) {
		location = "porch";
		for ( Coords field : fieldLocations ) {
			if ( field.calculateDistance( stormLocation ) < 500 ) {
				System.out.println( "No! My crops!" );
				return;
			}
		}
		System.out.println( "Hah, what's it going to do from over there!" );
	}

	private void getToTheCellar() {
		System.out.println( "Duck and cover!" );
		location = "cellar";
	}

	private static void welp() {
		System.out.println( "Oh the hubris!" );
		System.out.println( "*dies*" );
	}

	private static void rideItOut() {
		System.out.println( "I'm safe in here!" );
	}

}
