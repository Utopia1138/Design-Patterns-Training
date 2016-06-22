package com.spg.chapter4.factorymethod;

import com.spg.chapter1.engines.Engine;
import com.spg.chapter1.shields.Shield;
import com.spg.chapter1.ships.Fighter;
import com.spg.chapter1.ships.Spaceship;
import com.spg.chapter1.ships.Spaceship.Faction;
import com.spg.chapter1.weapons.Weapon;


public class RebelFactory extends SpaceshipFactory {

	@Override
	public Spaceship assembleShip( String name, Shield shield, Weapon weapon, Engine engine ) {
		Spaceship ship = new Fighter( name, shield, weapon, engine, Faction.REBEL );
		return ship;
	}

}
