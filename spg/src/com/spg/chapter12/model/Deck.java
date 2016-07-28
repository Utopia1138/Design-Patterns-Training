package com.spg.chapter12.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import com.spg.chapter12.controller.state.State;

public class Deck extends Observable {

	private List<State> deck;
	
	public Deck() {
		basicDeck();
	}

	public void resetDeck() {
		basicDeck();
	}

	private void basicDeck() {
		this.deck = new ArrayList<>();
		deck.add( State.BUILDER );
		deck.add( State.CAPTAIN );
		deck.add( State.CRAFTSMAN );
		deck.add( State.MAYOR );
		deck.add( State.PROSPECTOR );
		deck.add( State.PROSPECTOR );
		deck.add( State.SETTLER );
		deck.add( State.TRADER );		
	}
	
	public int getSize() {
		return deck.size();
	}

	public State getState( int id ) {
		State selected = deck.get( id );
		deck.remove( id );

		setChanged();
		notifyObservers();
		
		return selected;
	}
	
	public String displayDeck() {
		StringBuilder builder = new StringBuilder();

		builder.append( "Deck:\n" );

		for ( int i = 0; i < deck.size(); i++ ) {
			builder.append( i + ": " + deck.get( i ).getName() + "\n" );
		}
		
		return builder.toString();
	}
	
}
