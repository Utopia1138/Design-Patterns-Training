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
public class SniperScope extends WeaponMod {

	public SniperScope(Weapon base) {
		super(base);
	}

	@Override
	public double getAccuracy() {
		return base.getAccuracy() * 1.5;
	}

	@Override
	public double getRange() {
		return base.getRange() * 2;
	}

	@Override
	public String getDescription() {
		return "Scoped " + base.getDescription();
	}

}
