
package com.spg.chapter4.abstractfactory;

import com.spg.chapter1.engines.Engine;
import com.spg.chapter1.engines.InertialessEngine;
import com.spg.chapter1.shields.RayShield;
import com.spg.chapter1.shields.Shield;
import com.spg.chapter1.weapons.LaserWeapon;
import com.spg.chapter1.weapons.Weapon;

public class IncomCorporationFactory implements SpaceshipPartFactory {

	@Override
	public Weapon buildWeapon() {
		return new LaserWeapon();
	}

	@Override
	public Shield buildShield() {
		return new RayShield();
	}

	@Override
	public Engine buildEngine() {
		return new InertialessEngine();
	}

}
