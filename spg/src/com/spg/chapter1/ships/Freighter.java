package com.spg.chapter1.ships;

import com.spg.chapter1.engines.Engine;
import com.spg.chapter1.engines.InertialessEngine;
import com.spg.chapter1.shields.RayShield;
import com.spg.chapter1.shields.Shield;
import com.spg.chapter1.weapons.LaserWeapon;
import com.spg.chapter1.weapons.Weapon;

public class Freighter extends Spaceship {
	
	public Freighter( String name, Shield shield, Weapon weapon, Engine engine, Faction faction ) {
		setName( name );
		setHealth( 5000 );
		setShield( shield );
		setWeapon( weapon );
		setEngine( engine );
		setFaction( faction );
	}
}
