package com.spg.chapter1.ships;

import com.spg.chapter1.engines.IonEngine;
import com.spg.chapter1.shields.RayShield;
import com.spg.chapter1.weapons.RailgunWeapon;

public class Cruiser extends Spaceship {

	public Cruiser( String name ) {
		setName( name );
		setHealth( 100000 );
		setShield( new RayShield() );
		setWeapon( new RailgunWeapon() );
		setEngine( new IonEngine() );
	}
}
