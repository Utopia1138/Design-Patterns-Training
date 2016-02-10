
package com.spg.chapter4.abstractfactory;

import com.spg.chapter1.engines.Engine;
import com.spg.chapter1.shields.Shield;
import com.spg.chapter1.weapons.Weapon;

public interface SpaceshipPartFactory {

	public abstract Weapon buildWeapon();

	public abstract Shield buildShield();

	public abstract Engine buildEngine();

}
