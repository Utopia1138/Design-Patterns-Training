package org.axp.strategy;

import java.util.Random;

import org.axp.strategy.polling.TurnBased;
import org.axp.strategy.random.Dice;

public class Monopoly extends Game {
	private Random rand = new Random();
	private int turnCount = 0;
	
	public Monopoly( String...players ) {
		super( new TurnBased(), new Dice( 2 ), players );
	}
	
	protected void takeTurn( String player ) {
		getRandomOutcome( player ); // Roll the dice
				
		if ( rand.nextFloat() < 0.5 ) {
			System.out.println( player + " bought a property" );
		}
		
		if ( rand.nextFloat() < 0.2 ) {
			System.out.println( player + " built some houses" );
		}
		
		this.turnCount++;
	}
	
	protected boolean isGameEnded() {
		// TODO: code an actual winning condition
		return ( this.turnCount > 11 );
	}
}
