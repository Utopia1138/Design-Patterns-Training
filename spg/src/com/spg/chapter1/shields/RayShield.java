package com.spg.chapter1.shields;

import com.spg.chapter1.weapons.LaserWeapon;
import com.spg.chapter1.weapons.Weapon;

public class RayShield implements Shield {

	@Override
	public double mitigate( Weapon weapon ) {
		double mitigation = 0.7;
		
		// Ray shields better at blocking lasers 
		if ( weapon instanceof LaserWeapon  ) {
			mitigation = 0.5;
		}
		
		return weapon.shoot() * mitigation; 
	}

}
