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
	private int reloading;
	private int currentClip = 0;

	public Combatent(String name) {
		this.name = name;
		this.health = 1000;
		this.reloading = 0;
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

	public boolean alive() {
		return health > 0;
	}

	public void takeDamage(int damage) {
		health -= damage;
	}

	public CombatentState shoot(Combatent enemy, int distance, int chance) {
		if (reloading > 0) {
			reloading--;
			return CombatentState.RELOADING;
		}

		if (distance > weapon.getRange()) {
			return CombatentState.CLOSING_IN;
		}

		if (currentClip == 0) {
			reloading += weapon.getReloadSpeed();
			return CombatentState.RELOADING;
		}

		if (chance > weapon.getAccuracy()) {
			currentClip--;
			return CombatentState.MISS;
		}

		// If nothing has failed it means we can deal some damage!
		enemy.takeDamage(weapon.getDamage());
		return CombatentState.HIT;
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
