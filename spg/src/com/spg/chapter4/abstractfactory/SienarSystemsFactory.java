package com.spg.chapter4.abstractfactory;

import com.spg.chapter1.engines.Engine;
import com.spg.chapter1.engines.IonEngine;
import com.spg.chapter1.shields.RayShield;
import com.spg.chapter1.shields.Shield;
import com.spg.chapter1.weapons.MissleWeapon;
import com.spg.chapter1.weapons.Weapon;


public class SienarSystemsFactory implements SpaceshipPartFactory {

	@Override
	public Weapon buildWeapon() {
		return new MissleWeapon();
	}

	@Override
	public Shield buildShield() {
		return new RayShield();
	}

	@Override
	public Engine buildEngine() {
		return new IonEngine();
	}

}
