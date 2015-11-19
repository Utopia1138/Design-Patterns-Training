package org.axp.strategy.random;

import java.util.Random;

public class Dice implements RandomSource {
	private final int numDice;
	private final int sides;
	private Random rand = new Random();
	
	public Dice( int number, int sides ) {
		this.numDice = number;
		this.sides = sides;
	}
	
	public Dice( int number ) {
		this( number, 6 );
	}
	
	@Override
	public String outcome() {
		int sum = 0;
		
		for ( int i = 0; i < this.numDice; i++ ) {
			sum += rand.nextInt( this.sides ) + 1;
		}
		
		return "rolled a " + sum;
	}

	@Override
	public boolean hasMore() {
		return true; // Unlimited rolls. Mmmm.
	}
}
