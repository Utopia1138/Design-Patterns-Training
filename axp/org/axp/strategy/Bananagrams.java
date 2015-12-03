package org.axp.strategy;

import org.axp.strategy.polling.SimultaneousPlay;
import org.axp.strategy.random.LetterTiles;

public class Bananagrams extends Game {

	protected Bananagrams( String ... players ) {
		super( new SimultaneousPlay(), new LetterTiles(), players );
	}

	@Override
	protected boolean isGameEnded() {
		return !this.randomSource.hasMore();
	}

	@Override
	protected void takeTurn( String player ) {
		// In Bananagrams, whenever a player has completed their crossword, everyone *else* takes a tile
		System.out.println( player + " shouts 'peel'" );
		
		for ( String person : this.players ) {
			if ( !player.equals( person ) ) {
				getRandomOutcome( person );
			}
		}
		
		if ( isGameEnded() ) {
			System.out.println( player + " shouts 'bananas'!" );
		}
	}
}
