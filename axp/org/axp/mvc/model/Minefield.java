package org.axp.mvc.model;

import java.awt.Dimension;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Random;

public class Minefield implements Serializable {
	private static final long serialVersionUID = -2009952785658955431L;
	
	private final MineSquare[][] field;
	private final transient Random rand = new Random();
	private final HashSet<MineSquare> uncleared;
	
	public Minefield( int height, int width, int numMines ) {
		if ( height < 2 || height > 100 ) {
			throw new IllegalArgumentException( "Height should be between 2 and 100" );
		}
		
		if ( width < 2 || width > 100 ) {
			throw new IllegalArgumentException( "width should be between 2 and 100" );
		}
		
		if ( numMines == 0 ) {
			throw new IllegalArgumentException( "Some mines at least, please" );
		}
		
		if ( numMines > height * width / 2 ) {
			throw new IllegalArgumentException( "Way too many mines!" );
		}
		
		// Initialise the field
		field = new MineSquare[ height ][ width ];
		uncleared = new HashSet<>( height * width );
		
		for ( int j = 0; j < height; j++ ) {
			for ( int i = 0; i < width; i++ ) {
				field[j][i] = new MineSquare( j, i );
				uncleared.add( field[j][i] );
			}
		}
		
		// Fill with mines
		for ( int mineCount = 0; mineCount < numMines; ) {
			int j = rand.nextInt( height );
			int i = rand.nextInt( width );
			
			if ( !field[j][i].hasMine() ) {
				field[j][i].setHasMine( true );
				uncleared.remove( field[j][i] );
				mineCount++;
			}
		}
	}
	
	public synchronized void reveal( int ypos, int xpos ) {
		field[ ypos ][ xpos ].setRevealed( true );
		uncleared.remove( field[ ypos ][ xpos ] );
	}
	
	public synchronized int countNeighbourMines( int ypos, int xpos ) {
		if ( !field[ ypos ][ xpos ].isRevealed() ) {
			throw new IllegalStateException( "Can only count neighbours on a revealed square" );
		}
		if ( field[ ypos ][ xpos ].hasMine() ) {
			throw new IllegalStateException( "Can only count neighbours on square without a mine" );
		} 
		
		int count = 0;
		
		for ( int j = Math.max( 0, ypos - 1 ); j < Math.min( field.length, ypos + 2 ); j++ ) {
			for ( int i = Math.max( 0, xpos - 1 ); i < Math.min( field[0].length, xpos + 2 ); i++ ) {
				if ( field[j][i].hasMine() ) {
					count++;
				}
			}
		}
		
		return count;
	}
	
	public synchronized boolean isCleared() {
		return uncleared.isEmpty();
	}

	public MineSquare squareAt( int ypos, int xpos ) {
		return field[ ypos ][ xpos ];
	}

	public Dimension getDimensions() {
		return new Dimension( field[0].length, field.length );
	}
}
