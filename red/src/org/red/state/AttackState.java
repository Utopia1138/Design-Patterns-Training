package org.red.state;

/**
 * In the attack state, the hero can't fulfill any other actions
 * (actions occur during state transitions). In a more realistic
 * example, this would manage animation of the attack or
 * calculating hitboxes against other entities.
 *
 */
public class AttackState implements HeroState {

	private final Hero context;
	
	public AttackState( Hero context ) {
		this.context = context;
	}
	
	@Override
	public HeroState jump() {
		return this;
	}

	@Override
	public HeroState attack() {
		return this;
	}

	@Override
	public HeroState defend() {
		return this;
	}

	@Override
	public HeroState tick() {
		return new RestState( context );
	}

}
