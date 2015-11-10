package com.spg.chapter1.weapons;


public class MissleWeapon implements Weapon {

	int ammo = 50;
	
	@Override
	public int shoot() {
		// Missle does 200 damage, but only has fifty shots 
		if ( ammo > 0 ) {
			ammo--;
			return 200;
		}

		return 0;
	}

}
