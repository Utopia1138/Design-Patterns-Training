package org.axp.strategy;

import java.util.Arrays;
import java.util.List;

import org.axp.strategy.polling.PollingBehaviour;
import org.axp.strategy.random.RandomSource;

public abstract class Game {
	protected PollingBehaviour pollingBehaviour;
	protected RandomSource randomSource;
	protected List<String> players;
	
	protected Game( PollingBehaviour pollingBehaviour, RandomSource randomSource, String...playerNames ) {
		this.pollingBehaviour = pollingBehaviour;
		this.randomSource = randomSource;
		this.players = Arrays.asList( playerNames );
	}
	
	protected void getRandomOutcome( String player ) {
		System.out.println( player + " " + this.randomSource.outcome() );
	}
	
	protected abstract boolean isGameEnded();
	
	protected abstract void takeTurn( String player );
	
	public void runGame() {
		System.out.println( "Starting a game of " + getClass().getSimpleName() );
		
		while ( !isGameEnded() ) {
			String currentPlayer = pollingBehaviour.getNextPlayer( players );
			System.out.println( currentPlayer + " is starting play" );
			takeTurn( currentPlayer );
		}
		
		System.out.println( "The game is over\n" );
	}
}
