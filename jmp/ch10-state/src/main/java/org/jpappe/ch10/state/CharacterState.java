package org.jpappe.ch10.state;

public interface CharacterState {

	void takeDamage(int damage);
	
	void heal(int health);
	
	void revive(int health);
	
	double getEffectivenessMultiplier();
	
}
