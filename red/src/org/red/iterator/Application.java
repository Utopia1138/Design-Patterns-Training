package org.red.iterator;

import java.util.Random;

/**
 * Showing a couple of different patterns this time alongside iterator.
 * 
 * An object pool of actor state is created. In a more realistic example,
 * the state might be much more complex, and the reuse patterns much more
 * aggressive (think projectiles or particles, basically anything large
 * that shouldn't incur alloc/dealloc cost).
 * 
 * We use the flyweight pattern by separating out our polymorphic game
 * actors from the pooled state they can reuse. We get the advantage
 * of polymorphic game components with the performance benefits of
 * object pooling, contiguous storage (if only Java let us allocate
 * Objects directly in our array that is).
 * 
 * To operate on our actors, we use the visitor pattern. This is used
 * to pit heroes against monsters.
 * 
 */
public class Application {
	private static final Random RAND = new Random();
	private static final int ACTOR_COUNT = 20;

	public static void main(String[] args) {
		ActorPool pool = new ActorPool(ACTOR_COUNT);

		for ( int i = 0; i < ACTOR_COUNT; ++i ) {
			if ( RAND.nextBoolean() )
				pool.instance(Monster::new);
			else
				pool.instance(Hero::new);
		}

		System.out.println("Starting line up: ");
		// Old school iterator to show it works (we'll use streams after this point)
		for ( Actor actor : pool ) {
			System.out.println(actor);
		}
		System.out.println();
		
		int turn = 1;
		Counter counter;
		do {
			BattleVisitor battle = new BattleVisitor();
	
			// Fight!
			pool.stream().forEach(actor -> actor.accept(battle));
	
			// Weed out the dead
			pool.stream()
				.filter(actor -> actor.getHealth() < 1)
				.forEach(pool::release);
	
			System.out.println("End of round: " + turn++ );

			// Print them out
			pool.stream().forEach(System.out::println);
			System.out.println();

			// Count the remaining armies
			Counter nextCount = new Counter();
			pool.stream().forEach(actor -> actor.accept(nextCount));
			counter = nextCount;

		} while( counter.monsters > 0 && counter.heroes > 0 );

		if ( counter.monsters > 0 )
			System.out.println("Monsters win!");
		else
			System.out.println("Heroes win!");
	}

	public static class Counter implements ActorVisitor {
		public int monsters = 0;
		public int heroes = 0;
		
		@Override
		public void visit( Monster monster ) {
			monsters++;
		}

		@Override
		public void visit( Hero hero ) {
			heroes++;
		}
	}
}
