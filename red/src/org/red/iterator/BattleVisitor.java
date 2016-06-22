package org.red.iterator;

import java.util.Random;

public class BattleVisitor implements ActorVisitor {
	private static final Random RAND = new Random();
	private Hero hero;
	private Monster monster;

	@Override
	public void visit( Hero hero ) {
		this.hero = hero;
		if ( monster != null )
			fight( hero, monster );
	}

	@Override
	public void visit( Monster monster ) {
		this.monster = monster;
		if ( hero != null )
			fight( monster, hero );
	}

	public void fight( Actor a, Actor b ) {
		// A gets a slight pre-emptive strike bonus
		Actor attacked = RAND.nextDouble() > 0.6 ? a : b;
		attacked.takeDamage( (int) (RAND.nextDouble() * 33) );
	}
}
