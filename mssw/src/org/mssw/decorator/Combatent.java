/**
 * 
 */
package org.mssw.decorator;

import org.mssw.decorator.combatent.CombatentState;

/**
 * @author Mike
 *
 */
public class Combatent {
	private String name;
	private int health;
	private Weapon weapon;
	private long reloading;
	private int currentClip = 0;
	private int speed;
	private boolean fail = false;

	public Combatent(String name, int healthModifier, boolean positiveHealthMod, int speed) {
		this.name = name;
		this.health = this.initialiseHealth(healthModifier, positiveHealthMod);
		this.reloading = 0;
		this.speed = speed + 1;
	}
	
	private int initialiseHealth( int mod, boolean isPositive){
		int health = 1000;
		if( isPositive){
			health+= mod;
		}
		else{
			health -= mod;
		}
		return health;
	}

	public String getName() {
		return name;
	}

	public int getHealth() {
		return health;
	}

	public void setWeapon(Weapon weapon) {
		this.weapon = weapon;
		currentClip = weapon.getAmmoCapacity();
	}

	public Weapon getWeapon() {
		return weapon;
	}
	
	public int getSpeed(){
		return speed;
	}

	public boolean alive() {
		return health > 0;
	}

	public void takeDamage(int damage) {
		health -= damage;
	}
	
	public int getCurrentClip(){
		return currentClip;
	}

	public CombatentState shoot(Combatent enemy, int distance, int chance) {
		// Weapon is currently reloading.
		if (reloading > 0) {
			reloading--;
			
			// We've finished reloading now so add ammo to the clip
			if( reloading <= 0){
				currentClip = weapon.getAmmoCapacity();
			}
			return CombatentState.RELOADING;
		}

		// Are we within range to fire?
		if (distance > weapon.getRange()) {
			return CombatentState.CLOSING_IN;
		}

		// Do we have ammo to shoot?
		if (currentClip <= 0) {
			reloading = Math.round(weapon.getReloadSpeed());
			return CombatentState.RELOADING;
		}

		// Did we actually hit them?
		if (chance > weapon.getAccuracy()) {
			currentClip--;
			return CombatentState.MISS;
		}

		// If nothing has failed it means we can deal some damage!
		enemy.takeDamage(weapon.getDamage());
		return CombatentState.HIT;
	}
	
	public void fail(){
		this.fail = true;
	}
	
	public boolean failed(){
		return fail;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Combatent other = (Combatent) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
}
