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
public class Silencer extends WeaponMod {

	public Silencer(Weapon base) {
		super(base);
	}

	@Override
	public double getAccuracy() {
		return base.getAccuracy() * 1.1;
	};

	@Override
	public double getRange() {
		return base.getRange() * 0.75;
	};

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.mssw.decorator.Weapon#getDescription(java.lang.StringBuilder)
	 */
	@Override
	public String getDescription() {
		return "Silenced " + base.getDescription();
	}

}
