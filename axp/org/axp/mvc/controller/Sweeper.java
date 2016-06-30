package org.axp.mvc.controller;

import org.axp.mvc.model.IMinefield;

public class Sweeper implements ISweeper {
	IMinefield model;
	
	public Sweeper( IMinefield model ) {
		this.model = model;
	}
	
	public synchronized boolean uncover( int ypos, int xpos ) {
		model.reveal( ypos, xpos );
		return !model.hasMine( ypos, xpos );
	}
}
