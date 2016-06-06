
package com.spg.chapter10;

import java.util.ArrayList;
import java.util.List;

public class Table {

	public static void main( String[] args ) {

		PuertoPobre game = new PuertoPobre();

		List<Player> players = new ArrayList<>();

		// Create some players
		for ( int i = 1 ; i < 6 ; i++ ) {
			players.add( new Player( "Player " + i ) );
		}

		int pos = 0;

		while ( game.gameOn() ) {

			game.playTurn( players, pos );

			if ( pos == players.size() - 1 ) {
				pos = 0;
			}
			else {
				pos++;
			}

		}
		
		System.out.println( "Final Outcome: " );
		for( Player player : players ) {
			System.out.print( player.getName() + " got " + player.getScore() + " points!" );
			System.out.println( " Stats - Plantations: " + player.getPlantations() + " Factories: " + player.getFactories() + " Population: " + player.getPopulation()  );
		}
	}

}
