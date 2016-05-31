package org.jpappe.ch10.model;

import org.jpappe.ch10.action.HealthAction;
import org.jpappe.ch10.state.CharacterState;
import org.jpappe.ch10.state.HealthStateFactory;

public class CharacterEntity {

	private final int maxHealth;
	
	private int currentHealth;
	private double baseEffectiveness;
	
	private CharacterState currentState;
	
	
	public CharacterEntity(HealthStateFactory healthStateFactory, int maxHealth, double baseEffectiveness) {
		this.maxHealth = maxHealth;
		this.currentHealth = maxHealth;
		this.baseEffectiveness = baseEffectiveness;
		currentState = healthStateFactory.getHealthStateForCharacter(this);
	}
	
	/**
	 * Allows an action to be performed against this character
	 * @param action
	 */
	public void receiveAction(HealthAction action) {
		action.performAction(this);
	}

	/**
	 * get the effectiveness of the character
	 * @return effectiveness
	 */
	public double getEffectiveness() {
		return baseEffectiveness * currentState.getEffectivenessMultiplier();
	}

	public int getCurrentHealth() {
		return currentHealth;
	}


	public void setCurrentHealth(int currentHealth) {
		this.currentHealth = currentHealth;
	}


	public CharacterState getCurrentState() {
		return currentState;
	}


	public void setCurrentState(CharacterState currentState) {
		this.currentState = currentState;
	}


	public int getMaxHealth() {
		return maxHealth;
	}

}
