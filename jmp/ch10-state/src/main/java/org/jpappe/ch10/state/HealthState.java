package org.jpappe.ch10.state;

import org.jpappe.ch10.model.CharacterEntity;

public abstract class HealthState implements CharacterState {

	protected final CharacterEntity character;
	protected final HealthStateFactory factory;
	
	public HealthState(CharacterEntity c, HealthStateFactory h) {
		character = c;
		factory = h;
	}
	
	private void noOp(String action) {
		System.out.println(
				String.format("[%s] nothing to do for action %s", 
						this.getClass().getName(), 
						action ) );
	}

	@Override
	public void takeDamage(int damage) {
		this.noOp( "takeDamage" );
	}

	@Override
	public void heal(int health) {
		this.noOp( "heal" );
	}

	@Override
	public void revive(int health) {
		this.noOp( "revive" );
	}

	@Override
	public double getEffectivenessMultiplier() {
		return 0;
	}

}
