package com.spg.chapter12.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.spg.chapter12.model.Deck;
import com.spg.chapter12.model.Player;
import com.spg.chapter12.view.AITable;
import com.spg.chapter12.view.Table;

public class GameSetup {

	public static void main( String[] args ) throws IOException, InterruptedException {
		// Create one deck that everyone shares
		Deck deck = new Deck();
		
		List<Table> tables = new ArrayList<>();

		// Create some players
		for ( int i = 1 ; i < 6 ; i++ ) {
			Player player = new Player( "Player " + i );
			
			// Set up the player's tables
			if ( i == 1 ) {
				tables.add( new Table(deck, player) );
			}
			else {
				tables.add( new AITable(deck, player) );
			}
		}

		PuertoPobre game = new PuertoPobre();
		
		int pos = 0;

		while ( game.gameOn() ) {
			
			Thread.sleep(500);
			
			if ( pos == 0 ) {
				deck.resetDeck();
			} 

			int selection = tables.get( pos ).getSelection( pos );
			
			game.playTurn( tables, pos, deck.getState( selection ) );

			if ( pos == tables.size() - 1 ) {
				pos = 0;
				tables = rotatePlayers( tables );
			}
			else {
				pos++;
			}

		}
		
		System.out.println( "Final Outcome: " );
		for( Table player : tables ) {
			System.out.print( player.getPlayer().getName() + " got " + player.getPlayer().getScore() + " points!" );
			System.out.println( " Stats - Plantations: " + player.getPlayer().getPlantations() + " Factories: " + player.getPlayer().getFactories() + " Population: " + player.getPlayer().getPopulation() );
		}

	}
	
	public static List<Table> rotatePlayers( List<Table> tables ) {
		
		List<Table> newTables = new ArrayList<>();
		
		for( int i = 1; i < tables.size(); i++ ) {
			newTables.add( tables.get( i ) );
		}
		
		newTables.add( tables.get( 0 ) );
		
		return newTables;
	}

}
