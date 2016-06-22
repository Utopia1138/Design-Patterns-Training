package com.spg.chapter1.ships;

import com.spg.chapter1.engines.Engine;
import com.spg.chapter1.shields.Shield;
import com.spg.chapter1.weapons.Weapon;
import com.spg.chapter5.SingleAnnouncer;
import com.spg.chapter6.Projectile;
import com.spg.chapter6.ProjectileClass;

public abstract class Spaceship {
	// Needs engines, weapons, and shields
	private Weapon weapon;
	private Engine engine;
	private Shield shield;
	private int health;
	private String name;
	private Spaceship target;
	private Faction faction;
	
	// Buffs/debuffs
	protected double dodgeUpdate = 1.0; 
	protected double mitigationUpdate = 1.0; 
	
	public enum Faction {
		REBEL,
		EMPIRE;
	}
	
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

	public void damage( ProjectileClass projectile, int damage ) {
		
		// If we're dead what are we doing here?
		if ( getHealth() < 0 ) {
			return;
		}
		
		// Ship damage based on dodge chance and shield strength		
		
		// See if we were hit
		if ( (int) Math.ceil( Math.random() * 100 ) > ( engine.dodge() * dodgeUpdate ) )  {
			damage = (int) Math.floor( shield.mitigate( projectile, damage ) );

			setHealth( getHealth() - damage );

			System.out.println( name + " was hit for " + damage + " damage!" );

		}
		else {
			System.out.println( name + " dodges!" );

		}
	}
	
	public void explode() {
		System.out.println( name + " has exploded!" );
		
		SingleAnnouncer announcer = SingleAnnouncer.getInstance();
		announcer.speak( "Oh my, it looks like bits of " + name + " have been scattered all over " + announcer.getLocation() );
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
	
	public void setFaction( Faction faction ) {
		this.faction = faction;
	}
	
	public Faction getFaction() {
		return faction;
	}
	
	public Projectile shoot() {
		return weapon.shoot();
	}
	
	public abstract void dazzle();

	public abstract void activatePointDefence();

	public void knockback() {
		// TODO 
	}
}
