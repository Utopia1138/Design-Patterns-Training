package com.spg.chapter1.shields;

import com.spg.chapter1.weapons.LaserWeapon;
import com.spg.chapter1.weapons.Weapon;
import com.spg.chapter6.ProjectileClass;

public class RayShield implements Shield {

	@Override
	public double mitigate( ProjectileClass type, int damage ) {
		// Ray shields better at blocking energy, and they also confuse tracking weapons 
		// causing them to blow up before impact and do less damage
		switch( type ) {
			case ENERGY:
			return damage * 0.5;
			case TRACKING:
			return damage * 0.7;
			case PROJECTILE:
			default:
			return damage;
		}
	}

}
