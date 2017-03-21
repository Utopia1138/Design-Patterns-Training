
package com.spg.chapter12.view;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import com.spg.chapter12.controller.PuertoPobre;
import com.spg.chapter12.model.Deck;
import com.spg.chapter12.model.Player;

public class Table implements Observer {
	
	private Deck deck;
	private Player player;
	
	private String handDisplay;
	private String deckDisplay;
	
	public Table ( Deck deck, Player player ) {
		this.deck = deck;
		this.player = player;
		deck.addObserver( this );
		player.addObserver( this );
		
		deckDisplay = deck.displayDeck();
		handDisplay = player.displayHand();
	}

	@Override
	public void update( Observable obs, Object arg1 ) {
		if ( obs instanceof Player ) {
			this.handDisplay = ((Player) obs).displayHand();
		} 
		else if ( obs instanceof Deck ) {
			this.deckDisplay = ((Deck) obs).displayDeck();
		}
		updateDisplay();

	}

	public void updateDisplay() {
		System.out.println( deckDisplay );
		System.out.println( handDisplay );
	}
	
	public int getSelection( int pos ) throws IOException {
		
		updateDisplay();
		
		System.out.println( "You are the " + ( pos + 1 ) + "th player this turn" );
		
		int selection = deck.getSize();
		
		while ( selection >= deck.getSize() ) { 
			
			System.out.println( "Enter a card to select:" );
			char input = (char) System.in.read();
			System.in.skip( 2 );
			
			try {
				selection = Integer.parseInt( "" + input );
			}
			catch ( NumberFormatException e ) {
				// Do nothing, try again
			}
			
			System.out.println( "Selection:" + selection );
		}
		
		return selection;
	}

	public Player getPlayer() {
		return player;
	}
	
	public Deck getDeck() {
		return deck;
	}

}
