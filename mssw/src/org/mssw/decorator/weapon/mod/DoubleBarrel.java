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
public class DoubleBarrel extends WeaponMod {

	public DoubleBarrel(Weapon base) {
		super(base);
	}

	@Override
	public int getDamage() {
		return base.getDamage() * 2;
	}

	@Override
	public double getReloadSpeed() {
		return base.getReloadSpeed() * 2;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.mssw.decorator.Weapon#getDescription(java.lang.StringBuilder)
	 */
	@Override
	public String getDescription() {
		return "Double Barrelled " + base.getDescription();
	}

}
