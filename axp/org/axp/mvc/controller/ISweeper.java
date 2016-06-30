package org.axp.mvc.controller;

public interface ISweeper {
	/**
	 * Guess that a position is free of a mine, and uncover it
	 * @param ypos Y-position of the square in the field
	 * @param xpos X-position of the square in the field
	 * @return true if the square was cleared successfully, false if it contained a mine
	 * @throws ArrayIndexOutOfBoundsException if the position is not valid
	 */
	public boolean uncover( int ypos, int xpos );
}
