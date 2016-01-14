/**
 * 
 */
package org.mssw.decorator.weapon;

import org.mssw.decorator.Weapon;

/**
 * @author Mike
 *
 */
public class Rifle extends Weapon {

	@Override
	public double getAccuracy() {
		return 75;
	}

	@Override
	public int getDamage() {
		return 70;
	}

	@Override
	public double getReloadSpeed() {
		return 3;
	}

	@Override
	public double getRange() {
		return 85;
	}

	@Override
	public String getDescription() {
		return "Rifle";
	}

	@Override
	public int getAmmoCapacity() {
		return 10;
	}

}
