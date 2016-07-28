package com.spg.chapter12.view;

import com.spg.chapter12.model.Deck;
import com.spg.chapter12.model.Player;

public class AITable extends Table {

	public AITable( Deck deck, Player player ) {
		super( deck, player );
	}
	
	@Override
	public void updateDisplay() {
		// Does nothing for AI players
	}
	
	@Override
	public int getSelection( int pos ) {
		// AI selects an option at random
		int selection = getDeck().getSize();
		
		while ( selection >= getDeck().getSize() ) { 
			
			selection = (int) Math.ceil( Math.random() * 10 );
		}

		return selection;
	}
}
