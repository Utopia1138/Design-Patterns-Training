package org.red.state;

/**
 * The flying attack state continues the
 * jump laid out in a jump state whilst also
 * attacking (does nothing at the moment, but could
 * calculate hits against other entities).
 *
 */
public class FlyingAttackState implements HeroState {

	private final Hero context;
	private final long interval;
	private final long startTime;
	
	public FlyingAttackState( Hero context, long interval, long startTime ) {
		this.context = context;
		this.interval = interval;
		this.startTime = startTime;
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
		long time = System.currentTimeMillis();

		if ( time - startTime > interval )
			return new RestState(context);

		return this;
	}

}
