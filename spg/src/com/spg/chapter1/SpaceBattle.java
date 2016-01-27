package com.spg.chapter1;

import java.util.ArrayList;
import java.util.List;

import com.spg.chapter1.engines.InertialessEngine;
import com.spg.chapter1.engines.IonEngine;
import com.spg.chapter1.shields.RayShield;
import com.spg.chapter1.ships.Cruiser;
import com.spg.chapter1.ships.Freighter;
import com.spg.chapter1.ships.Spaceship;
import com.spg.chapter1.weapons.LaserWeapon;
import com.spg.chapter1.weapons.RailgunWeapon;

public class SpaceBattle {

	public static void main( String[] args ) {

		List<Spaceship> ships = new ArrayList<Spaceship>();
		ships.add( new Cruiser( "Imp", new RayShield(), new RailgunWeapon(), new IonEngine(), null) );
		ships.add( new Freighter( "Eagle", new RayShield(), new LaserWeapon(), new InertialessEngine(), null ) );
		
		ships.get( 0 ).setTarget( ships.get( 1 ) );
		ships.get( 1 ).setTarget( ships.get( 0 ) );
		
		while ( true ) {
			
			for (Spaceship ship: ships) {
					ship.getTarget().damage( ship.shoot() );
					
					if ( ship.getTarget().getHealth() < 0 ) {
						ship.getTarget().explode();
						return;
					}
			}
			
		}
		
	}

}
