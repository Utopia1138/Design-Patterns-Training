package org.red.state;

public class RestState implements HeroState {

	private final Hero context;
	
	public RestState( Hero context ) {
		this.context = context;
	}
	
	@Override
	public HeroState jump() {
		System.out.println( "Hero leaps into the air!" );
		return new JumpState( context );
	}

	@Override
	public HeroState attack() {
		System.out.println( "Hero attacks with a mighty swing" );
		return new AttackState( context );
	}

	@Override
	public HeroState defend() {
		System.out.println( "Hero defends with her shield" );
		return new DefendState( context );
	}

	@Override
	public HeroState tick() {
		return this;
	}

}
