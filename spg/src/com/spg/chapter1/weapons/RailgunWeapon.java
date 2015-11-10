package com.spg.chapter1.weapons;


public class RailgunWeapon implements Weapon {

	int ammo = 2000;
	
	@Override
	public int shoot() {
		// Railgun does less damage but has more ammo than missile
		if ( ammo > 0 ) {
			ammo--;
			return 50;
		}

		return 0;
	}

}
