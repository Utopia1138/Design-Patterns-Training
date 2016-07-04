package org.axp.mvc.controller;

import java.util.Observable;

import org.axp.mvc.model.IMinefield;
import org.axp.mvc.model.MineSquare;

public class Sweeper extends Observable  implements ISweeper {
	IMinefield model;
	
	public Sweeper( IMinefield model ) {
		this.model = model;
	}
	
	public synchronized boolean uncover( int ypos, int xpos ) {
		model.reveal( ypos, xpos );
		MineSquare square = model.squareAt( ypos, xpos );
		broadcastSquare( square );
		return !square.hasMine();
	}
	
	private void broadcastSquare( MineSquare square ) {
		setChanged();
		notifyObservers( square );
	}

	@Override
	public int countNeighbourMines( int ypos, int xpos ) {
		return model.countNeighbourMines( ypos, xpos );
	}
}
