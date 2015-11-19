package org.axp.strategy;

import java.util.Random;

import org.axp.strategy.polling.TurnBased;
import org.axp.strategy.random.LetterTiles;

public class Scrabble extends Game {
	private Random rand = new Random();
	
	protected Scrabble( String ... players ) {
		super( new TurnBased(), new LetterTiles(), players );
	}

	@Override
	protected boolean isGameEnded() {
		return !this.randomSource.hasMore();
	}

	@Override
	protected void takeTurn( String player ) {
		int numLetters = rand.nextInt( 5 ) + 2;
		
		System.out.printf( "%s played a %d-letter word\n", player, numLetters );
		
		for ( int i = 1; i < numLetters; i++ ) {
			getRandomOutcome( player ); // Draw back up to seven
		}
	}
}
