package com.spg.chapter1.shields;

import com.spg.chapter1.weapons.Weapon;
import com.spg.chapter6.ProjectileClass;

public class ParticleShield implements Shield {

	@Override
	public double mitigate( ProjectileClass type, int damage ) {
		// Blocks non-laser weapons well, laser weapons poorly
		switch( type ) {
			case ENERGY:
			return damage * 0.8;
			case PROJECTILE:
			case TRACKING:
			return damage * 0.2;
			default:
			return damage;
		}

	}

}
