/**
 * 
 */
package org.mssw.decorator.weapon.mod;

import org.mssw.decorator.Weapon;
import org.mssw.decorator.weapon.WeaponMod;

/**
 * @author Mike
 *
 */
public class Rusty extends WeaponMod {

	public Rusty(Weapon base) {
		super(base);
	}

	@Override
	public double getAccuracy() {
		return base.getAccuracy() / 2;
	}

	@Override
	public int getDamage() {
		return base.getDamage();
	}

	@Override
	public double getRange() {
		return base.getRange() / 2;
	}

	@Override
	public double getReloadSpeed() {
		return base.getReloadSpeed() * 2;
	};

	@Override
	public String getDescription() {
		return "Rusty " + base.getDescription();
	}

}
