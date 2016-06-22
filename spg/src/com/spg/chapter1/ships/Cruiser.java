package com.spg.chapter1.ships;

import com.spg.chapter1.engines.Engine;
import com.spg.chapter1.engines.IonEngine;
import com.spg.chapter1.shields.RayShield;
import com.spg.chapter1.shields.Shield;
import com.spg.chapter1.weapons.RailgunWeapon;
import com.spg.chapter1.weapons.Weapon;

public class Cruiser extends Spaceship {

	public Cruiser( String name, Shield shield, Weapon weapon, Engine engine, Faction faction ) {
		setName( name );
		setHealth( 100000 );
		setShield( shield );
		setWeapon( weapon );
		setEngine( engine );
		setFaction( faction );
	}

	@Override
	public void dazzle() {
		System.out.println( "Argh! Why did we install this huge window in the front." );
		this.dodgeUpdate -= 0.10;
	}

	@Override
	public void activatePointDefence() {
		
		// 20% chance of shooting down incoming missile
		if ( (int) Math.ceil( Math.random() * 100 ) < 20 ) {
			System.out.println( "Shoot it down" );
			this.mitigationUpdate = 0.0;
		}
	}
}
