package com.spg.chapter1.shields;

import com.spg.chapter1.weapons.Weapon;

public class ParticleShield implements Shield {

	@Override
	public double mitigate( Weapon weapon ) {
		// Blocks a fixed amount
		return weapon.shoot() * 0.2;
	}

}
