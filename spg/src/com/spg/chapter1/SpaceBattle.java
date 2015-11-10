package com.spg.chapter1;

import java.util.ArrayList;
import java.util.List;

import com.spg.chapter1.ships.Cruiser;
import com.spg.chapter1.ships.Freighter;
import com.spg.chapter1.ships.Spaceship;

public class SpaceBattle {

	public static void main( String[] args ) {

		List<Spaceship> ships = new ArrayList<Spaceship>();
		ships.add( new Cruiser( "Imp" ) );
		ships.add( new Freighter( "Eagle" ) );
		
		ships.get( 0 ).setTarget( ships.get( 1 ) );
		ships.get( 1 ).setTarget( ships.get( 0 ) );
		
		LOOP:
		while ( true ) {
			
			for (Spaceship ship: ships) {
					ship.getTarget().damage( ship.shoot() );
					
					if ( ship.getTarget().getHealth() < 0 ) {
						ship.getTarget().explode();
						break LOOP;
					}
			}
			
		}
		
	}

}
