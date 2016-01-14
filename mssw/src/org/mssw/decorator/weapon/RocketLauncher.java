/**
 * 
 */
package org.mssw.decorator.weapon;

import org.mssw.decorator.Weapon;

/**
 * @author Mike
 *
 */
public class RocketLauncher extends Weapon {

	@Override
	public double getAccuracy() {
		return 45;
	}

	@Override
	public int getDamage() {
		return 200;
	}

	@Override
	public double getReloadSpeed() {
		return 9;
	}

	@Override
	public double getRange() {
		return 80;
	}

	@Override
	public String getDescription() {
		return "Rocket Launcher";
	}

	@Override
	public int getAmmoCapacity() {
		return 1;
	}

}
