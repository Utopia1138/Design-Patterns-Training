package org.jpappe.ch10.model;

import org.jpappe.ch10.state.CharacterState;

public class CharacterEntity {

	private final int maxHealth;
	
	private int currentHealth;
	
	private CharacterState currentState;
	
	
	public CharacterEntity(int maxHealth) {
		this.maxHealth = maxHealth;
		this.currentHealth = maxHealth;
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
