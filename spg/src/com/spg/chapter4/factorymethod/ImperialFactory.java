
package com.spg.chapter4.factorymethod;

import com.spg.chapter1.engines.Engine;
import com.spg.chapter1.shields.Shield;
import com.spg.chapter1.ships.Cruiser;
import com.spg.chapter1.ships.Spaceship;
import com.spg.chapter1.ships.Spaceship.Faction;
import com.spg.chapter1.weapons.Weapon;

public class ImperialFactory extends SpaceshipFactory {

	@Override
	public Spaceship assembleShip( String name, Shield shield, Weapon weapon, Engine engine ) {
		return new Cruiser( name, shield, weapon, engine, Faction.EMPIRE );
	}

}
