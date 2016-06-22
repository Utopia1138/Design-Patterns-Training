package com.spg.chapter1.weapons;

import com.spg.chapter6.Projectile;
import com.spg.chapter6.ProjectileClass;

public class RailgunWeapon implements Weapon {

	int ammo = 2000;
	
	@Override
	public Projectile shoot() {
		// Railgun does less damage but has more ammo than missile
		if ( ammo > 0 ) {
			ammo--;
			return (target)->{ target.knockback(); target.damage( ProjectileClass.PROJECTILE, 500 ); };
		}

		return (target)->{ target.damage( ProjectileClass.PROJECTILE, 0 ); };
	}

}
