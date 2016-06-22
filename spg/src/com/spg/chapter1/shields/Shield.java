package com.spg.chapter1.shields;

import com.spg.chapter1.weapons.Weapon;
import com.spg.chapter6.ProjectileClass;

public interface Shield {
	public double mitigate( ProjectileClass type, int damage );
}
