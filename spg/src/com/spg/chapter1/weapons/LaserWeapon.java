package com.spg.chapter1.weapons;

import java.lang.annotation.Target;

import com.spg.chapter6.Projectile;
import com.spg.chapter6.ProjectileClass;

public class LaserWeapon implements Weapon {

	@Override
	public Projectile shoot() {
		
		// The laser does a flat 1000 damage and gets in the enemy's eyes
		return (target)->{ target.dazzle(); target.damage( ProjectileClass.ENERGY, 1000 ); };
	}

}
