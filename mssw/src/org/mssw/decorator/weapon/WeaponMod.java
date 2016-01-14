/**
 * 
 */
package org.mssw.decorator.weapon;

import org.mssw.decorator.Weapon;

/**
 * @author Mike
 *
 */
public abstract class WeaponMod extends Weapon {
	protected Weapon base;

	public WeaponMod(Weapon base) {
		this.base = base;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.mssw.decorator.Weapon#getAccuracy()
	 */
	@Override
	public double getAccuracy() {
		return base.getAccuracy();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.mssw.decorator.Weapon#getDamage()
	 */
	@Override
	public int getDamage() {
		return base.getDamage();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.mssw.decorator.Weapon#getReloadSpeed()
	 */
	@Override
	public double getReloadSpeed() {
		return base.getReloadSpeed();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.mssw.decorator.Weapon#getRange()
	 */
	@Override
	public double getRange() {
		return base.getRange();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.mssw.decorator.Weapon#getAmmoCapacity()
	 */
	@Override
	public int getAmmoCapacity() {
		return base.getAmmoCapacity();
	}

}
