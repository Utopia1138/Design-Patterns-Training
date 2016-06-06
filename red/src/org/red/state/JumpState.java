package org.red.state;

/**
 * The jump state will return to rest after a delay
 * and may transition to a jump attack state.
 */
public class JumpState implements HeroState {

	private final Hero context;
	private static final long interval = 200L;
	private final long startTime = System.currentTimeMillis();
	
	public JumpState( Hero context ) {
		this.context = context;
	}
	
	@Override
	public HeroState jump() {
		return this;
	}

	@Override
	public HeroState attack() {
		System.out.println( "Hero unleashes a flying attack!" );
		return new FlyingAttackState( context, interval, startTime );
	}

	@Override
	public HeroState defend() {
		return this;
	}

	@Override
	public HeroState tick() {
		long time = System.currentTimeMillis();

		if ( time - startTime > interval )
			return new RestState( context );

		return this;
	}

}
