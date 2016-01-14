package org.mssw.decorator.weapon;

import org.mssw.decorator.Weapon;

/**
 * @author Mike
 *
 */
public class Shotgun extends Weapon {

	@Override
	public double getAccuracy() {
		return 25;
	}

	@Override
	public int getDamage() {
		return 80;
	}

	@Override
	public double getReloadSpeed() {
		return 8;
	}

	@Override
	public double getRange() {
		return 15;
	}

	@Override
	public String getDescription() {
		return "Shotgun";
	}

	@Override
	public int getAmmoCapacity() {
		return 7;
	}

}
