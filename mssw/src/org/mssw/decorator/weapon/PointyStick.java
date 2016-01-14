/**
 * 
 */
package org.mssw.decorator.weapon;

import org.mssw.decorator.Weapon;

/**
 * @author Mike
 *
 */
public class PointyStick extends Weapon {

	/* (non-Javadoc)
	 * @see org.mssw.decorator.Weapon#getAccuracy()
	 */
	@Override
	public double getAccuracy() {
		return 50;
	}

	/* (non-Javadoc)
	 * @see org.mssw.decorator.Weapon#getDamage()
	 */
	@Override
	public int getDamage() {
		return 99999;
	}

	/* (non-Javadoc)
	 * @see org.mssw.decorator.Weapon#getReloadSpeed()
	 */
	@Override
	public double getReloadSpeed() {
		return 0;
	}

	/* (non-Javadoc)
	 * @see org.mssw.decorator.Weapon#getRange()
	 */
	@Override
	public double getRange() {
		return 0;
	}

	/* (non-Javadoc)
	 * @see org.mssw.decorator.Weapon#getAmmoCapacity()
	 */
	@Override
	public int getAmmoCapacity() {
		return 99999;
	}

	/* (non-Javadoc)
	 * @see org.mssw.decorator.Weapon#getDescription()
	 */
	@Override
	public String getDescription() {
		return "pointy stick...";
	}

}
