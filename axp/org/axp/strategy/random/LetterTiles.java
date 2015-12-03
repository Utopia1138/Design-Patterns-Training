package org.axp.strategy.random;

import java.util.Collections;
import java.util.LinkedList;

public class LetterTiles implements RandomSource {
	private LinkedList<Character> tiles;
	
	public LetterTiles() {
		this.tiles = new LinkedList<Character>();
		
		// Scrabble(TM) distribution
		addTiles( 12, 'E' );
		addTiles(  9, 'A', 'I' );
		addTiles(  8, 'O' );
		addTiles(  6, 'N', 'R', 'T' );
		addTiles(  4, 'L', 'S', 'U', 'D' );
		addTiles(  3, 'G' );
		addTiles(  2, 'B', 'C', 'M', 'P', 'F', 'H', 'V', 'W', 'Y' );
		addTiles(  1, 'K', 'J', 'X', 'Q', 'Z' );
		
		Collections.shuffle( tiles );
	}
	
	private void addTiles( int count, char...letters ) {
		for ( char c : letters ) {
			for ( int i = 0; i < count; i++ ) {
				this.tiles.add( c );
			}
		}
	}
	
	@Override
	public String outcome() {
		if ( hasMore() ) {
			return "drew a '" + this.tiles.pop() + "'";
		}
		
		return "can't draw; no more tiles!";
	}

	@Override
	public boolean hasMore() {
		return !this.tiles.isEmpty();
	}

}
