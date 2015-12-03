package com.spg.chapter1.weapons;


public class LaserWeapon implements Weapon {

	@Override
	public int shoot() {
		
		// The laser does a flat 1000 damage
		return 1000;
	}

}
