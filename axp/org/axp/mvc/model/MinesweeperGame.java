package org.axp.mvc.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

public class MinesweeperGame implements MinesweeperModel {
	private final Minefield field;
	private HashSet<Player> players = new HashSet<>();
	
	public MinesweeperGame( Minefield field ) {
		this.field = field;
	}
	
	@Override
	public int countNeighbourMines( int ypos, int xpos ) {
		return field.countNeighbourMines( ypos, xpos );
	}

	@Override
	public boolean isCleared() {
		return field.isCleared();
	}

	@Override
	public void reveal(int ypos, int xpos) {
		field.reveal( ypos, xpos );
	}

	@Override
	public MineSquare squareAt( int ypos, int xpos ) {
		return field.squareAt( ypos, xpos );
	}

	@Override
	public Minefield getField() {
		return field;
	}

	@Override
	public Player addNewPlayer() {
		Player p;
		do {
			p = new Player();
		}
		while ( players.contains( p ) );
		
		players.add( p );
		return p;
	}

	@Override
	public void removePlayer( Player p ) {
		players.remove( p );
	}

	@Override
	public void addPoint( Player p ) {
		p.incrementScore();
	}

	@Override
	public List<Player> getScores() {
		ArrayList<Player> playersByScore = new ArrayList<>( players );
		Collections.sort( playersByScore, (x,y) -> x.getScore() - y.getScore() );
		return playersByScore;
	}
}
