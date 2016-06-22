
package com.spg.chapter4;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.spg.chapter1.ships.Spaceship;
import com.spg.chapter4.abstractfactory.IncomCorporationFactory;
import com.spg.chapter4.abstractfactory.SienarSystemsFactory;
import com.spg.chapter4.factorymethod.ImperialFactory;
import com.spg.chapter4.factorymethod.RebelFactory;
import com.spg.chapter4.factorymethod.SpaceshipFactory;
import com.spg.chapter5.SingleAnnouncer;

public class SpaceshipArena {

	public static void main( String[] args ) throws Exception {

		List<Spaceship> ships = new ArrayList<Spaceship>();
		
		// Our first battle takes place in a space junkyard
		SingleAnnouncer announcer = SingleAnnouncer.getInstance();
		announcer.setLocation( "a space junkyard" );

		// Here we have some factories
		SpaceshipFactory empire = new ImperialFactory();
		SpaceshipFactory rebels = new RebelFactory();

		// They get parts from different providers
		empire.setPartProvider( new SienarSystemsFactory() );
		rebels.setPartProvider( new IncomCorporationFactory() );

		// Build 2 cruisers vs 5 fighters
		ships.add( empire.constructShip( "Imp 1" ) );
		ships.add( empire.constructShip( "Imp 2" ) );
		ships.add( rebels.constructShip( "Red 1" ) );
		ships.add( rebels.constructShip( "Red 2" ) );
		ships.add( rebels.constructShip( "Red 3" ) );
		ships.add( rebels.constructShip( "Red 4" ) );
		ships.add( rebels.constructShip( "Red 5" ) );

		// Pick targets
		for ( Spaceship ship : ships ) {
			setTarget( ship, ships );
		}

		// Fite nite!
		battle( ships );

		// The factions decide to try different suppliers
		ships = new ArrayList<Spaceship>();
		empire.setPartProvider( new IncomCorporationFactory() );
		rebels.setPartProvider( new SienarSystemsFactory() );
		
		// This time the battle takes place in deep space
		announcer.setLocation( "deep space" );

		// Let's set up one cruiser against ten fighters and see who wins
		ships.add( empire.constructShip( "Imp 1" ) );
		ships.add( rebels.constructShip( "Red 1" ) );
		ships.add( rebels.constructShip( "Red 2" ) );
		ships.add( rebels.constructShip( "Red 3" ) );
		ships.add( rebels.constructShip( "Red 4" ) );
		ships.add( rebels.constructShip( "Red 5" ) );
		ships.add( rebels.constructShip( "Red 6" ) );
		ships.add( rebels.constructShip( "Red 7" ) );
		ships.add( rebels.constructShip( "Red 8" ) );
		ships.add( rebels.constructShip( "Red 9" ) );
		ships.add( rebels.constructShip( "Red 10" ) );

		// Pick targets
		for ( Spaceship ship : ships ) {
			setTarget( ship, ships );
		}

		// Fite nite!
		battle( ships );
	}

	public static void setTarget( Spaceship ship, List<Spaceship> ships ) {
		ship.setTarget( null );

		for ( Spaceship potentialTarget : ships ) {
			if ( potentialTarget.getFaction() != ship.getFaction() && potentialTarget.getHealth() > 0 ) {
				ship.setTarget( potentialTarget );
				break;
			}
		}
	}

	public static void battle( List<Spaceship> ships ) throws Exception {
		SingleAnnouncer announcer = SingleAnnouncer.getInstance();
		announcer.speak( "The battle begins in " + announcer.getLocation() );
		
		while ( true ) {
			for ( Spaceship ship : ships ) {
				if ( ship.getHealth() < 0 ) {
					continue;
				}

				// If a ship has no target then it's all over
				if ( ship.getTarget() == null ) {
					System.out.println( "End of battle" );
					return;
				}

				ship.shoot().execute( ship.getTarget() );

				if ( ship.getTarget().getHealth() < 0 ) {
					ship.getTarget().explode();
					setTarget( ship, ships );
				}
			}
			Thread.sleep( 500 );
		}
	}

}
