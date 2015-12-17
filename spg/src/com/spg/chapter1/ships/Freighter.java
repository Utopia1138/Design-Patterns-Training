package com.spg.chapter1.ships;

import com.spg.chapter1.engines.InertialessEngine;
import com.spg.chapter1.shields.RayShield;
import com.spg.chapter1.weapons.LaserWeapon;

public class Freighter extends Spaceship {
	
	public Freighter( String name ) {
		setName( name );
		setHealth( 5000 );
		setShield( new RayShield() );
		setWeapon( new LaserWeapon() );
		setEngine( new InertialessEngine() );
	}
}
