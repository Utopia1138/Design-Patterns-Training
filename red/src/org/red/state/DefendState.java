package org.red.state;

/**
 * Similar to the attack state, the defend state
 * has no actions and quickly returns to the rest
 * state.
 *
 */
public class DefendState implements HeroState {

	private final Hero context;
	
	public DefendState( Hero context ) {
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
