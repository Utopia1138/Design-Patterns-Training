
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

}
