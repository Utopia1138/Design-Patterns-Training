
package com.spg.chapter4.factorymethod;

import com.spg.chapter1.engines.Engine;
import com.spg.chapter1.shields.Shield;
import com.spg.chapter1.ships.Spaceship;
import com.spg.chapter1.weapons.Weapon;
import com.spg.chapter4.abstractfactory.SpaceshipPartFactory;
import com.spg.chapter5.SingleAnnouncer;

public abstract class SpaceshipFactory {

	private SpaceshipPartFactory partProvider;

	public Spaceship constructShip( String name ) {
		System.out.println( "Placing order with parts provider" );

		System.out.println( "Assembling starship " + name );
		SingleAnnouncer announcer = SingleAnnouncer.getInstance();
		announcer.speak( "A new ship rolls off the production lines and heads out to do battle in " + announcer.getLocation() );

		return assembleShip( name, partProvider.buildShield(), partProvider.buildWeapon(), partProvider.buildEngine() );
	}

	public void setPartProvider( SpaceshipPartFactory provider ) {
		this.partProvider = provider;
	}

	public abstract Spaceship assembleShip( String name, Shield shield, Weapon weapon, Engine engine );
}
