package com.spg.chapter1.weapons;

import com.spg.chapter6.Projectile;
import com.spg.chapter6.ProjectileClass;

public class MissleWeapon implements Weapon {

	int ammo = 50;
	
	@Override
	public Projectile shoot() {
		// Missle does 2000 damage, but only has fifty shots 
		if ( ammo > 0 ) {
			ammo--;
			return (target)->{ target.activatePointDefence(); target.damage( ProjectileClass.TRACKING, 2000 ); };
		}

		return (target)->{ target.damage( ProjectileClass.TRACKING, 0 ); };
	}

}
