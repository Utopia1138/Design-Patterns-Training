/**
 * 
 */
package org.mssw.decorator;

/**
 * @author Mike
 *
 */
public abstract class Weapon {

	/**
	 * % chance of a hit (0-100)
	 * 
	 * @return
	 */
	public abstract double getAccuracy();

	/**
	 * Amount of damage inflicted by a hit
	 * 
	 * @return
	 */
	public abstract int getDamage();

	/**
	 * The length of time to reload (number of in game ticks)
	 * 
	 * @return
	 */
	public abstract double getReloadSpeed();

	/**
	 * The distance you need to be before you can hit the target
	 * 
	 * @return
	 */
	public abstract double getRange();

	/**
	 * The amount of times you can consecutively shoot before needing to reload
	 * 
	 * @return
	 */
	public abstract int getAmmoCapacity();

	/**
	 * A description of the weapon and any Mods
	 * 
	 * @return
	 */
	public abstract String getDescription();

}
