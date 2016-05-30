package org.jpappe.ch10.state;

import org.jpappe.ch10.model.CharacterEntity;

public class Dead extends HealthState {

	public Dead(CharacterEntity c, HealthStateFactory f) {
		super(c,f);
	}

	@Override
	public void takeDamage(int damage) {
		System.out.println("You are beating a dead horse.");
	}

	@Override
	public void heal(int health) {
		System.out.println("You can't heal the dead. It would take a miracle.");

	}

	@Override
	public void revive(int health) {
		character.setCurrentHealth(health);
		character.setCurrentState(
				factory.getHealthStateForCharacter(character));
	}

}
