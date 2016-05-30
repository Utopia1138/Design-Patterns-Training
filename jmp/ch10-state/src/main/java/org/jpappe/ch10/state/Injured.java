package org.jpappe.ch10.state;

import org.jpappe.ch10.model.CharacterEntity;

public class Injured extends HealthState {

	public Injured(CharacterEntity c, HealthStateFactory f) {
		super(c, f);
	}

	@Override
	public void takeDamage(int damage) {
		character.setCurrentHealth(Math.max(character.getCurrentHealth() - damage, 0));
		character.setCurrentState(factory.getHealthStateForCharacter(character));
	}

	@Override
	public void heal(int health) {
		character.setCurrentHealth(Math.min(character.getCurrentHealth() + health, character.getMaxHealth()));
		character.setCurrentState(factory.getHealthStateForCharacter(character));
	}

	@Override
	public double getEffectivenessMultiplier() {
		return 0.5;
	}

}
