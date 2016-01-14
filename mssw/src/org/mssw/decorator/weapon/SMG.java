/**
 * 
 */
package org.mssw.decorator.weapon;

import org.mssw.decorator.Weapon;

/**
 * @author Mike
 *
 */
public class SMG extends Weapon {

	@Override
	public double getAccuracy() {
		return 35;
	}

	@Override
	public int getDamage() {
		return 15;
	}

	@Override
	public double getReloadSpeed() {
		return 1;
	}

	@Override
	public double getRange() {
		return 40;
	}

	@Override
	public String getDescription() {
		return "SMG";
	}

	@Override
	public int getAmmoCapacity() {
		return 50;
	}

}
