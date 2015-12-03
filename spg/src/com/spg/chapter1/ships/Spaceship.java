package com.spg.chapter1.ships;

import com.spg.chapter1.engines.Engine;
import com.spg.chapter1.shields.Shield;
import com.spg.chapter1.weapons.Weapon;

public abstract class Spaceship {
	// Needs engines, weapons, and shields
	private Weapon weapon;
	private Engine engine;
	private Shield shield;
	private int health;
	private String name;
	private Spaceship target;
	
	protected void setName( String name ){
		this.name = name;
	}
	
	public void setWeapon( Weapon weapon ) {
		this.weapon = weapon;
	}
	
	public void setEngine( Engine engine ) {
		this.engine = engine;
	}

	public void setShield( Shield shield ) {
		this.shield = shield;
	}

	public void damage( Weapon damager ) {
		// Ship damage based on dodge chance and shield strength
		
		int damage = 0;
		
		// See if we were hit
		if ( (int) Math.ceil( Math.random() * 100 ) > engine.dodge() )  {
			damage = (int) Math.floor( shield.mitigate( damager ) );

			setHealth( getHealth() - damage );

			System.out.println( name + " was hit for " + damage + " damage!" );

		}
		else {
			System.out.println( name + " dodges!" );

		}
	}
	
	public void explode() {
		System.out.println( name + " has exploded!" );
	}

	public int getHealth() {
		return health;
	}

	public void setHealth( int health ) {
		this.health = health;
	}

	public Spaceship getTarget() {
		return target;
	}

	public void setTarget( Spaceship target ) {
		this.target = target;
	}
	
	public Weapon shoot() {
		return weapon;
	}
}
