
package com.spg.chapter1.ships;

import com.spg.chapter1.engines.Engine;
import com.spg.chapter1.shields.Shield;
import com.spg.chapter1.weapons.Weapon;

public class Fighter extends Spaceship {

	public Fighter( String name, Shield shield, Weapon weapon, Engine engine, Faction faction ) {
		setName( name );
		setHealth( 3000 );
		setShield( shield );
		setWeapon( weapon );
		setEngine( engine );
		setFaction( faction );
	}

	
	@Override
	public void dazzle() {
		System.out.println( "Argh! *flails wildly at the controls*" );
		this.dodgeUpdate += 0.20;
	}


	@Override
	public void activatePointDefence() {
		System.out.println( "Firing flare!" );
		
		// Missile has 50-50 chance of picking flare or fighter
		if ( (int) Math.ceil( Math.random() * 100 ) < 50 ) {
			this.dodgeUpdate += 100.0;
		}

	}

}
