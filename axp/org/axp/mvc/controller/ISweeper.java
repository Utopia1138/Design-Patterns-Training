package org.axp.mvc.controller;

import java.util.Observer;

public interface ISweeper {
	/**
	 * Count the number of neighbouring mines in a square. Can only be called on uncovered, mine-free squares.
	 * @param ypos Y-position of the square in the field
	 * @param xpos X-position of the square in the field
	 * @return the number of neighbouring squares with a mine
	 * @throws IllegalStateException if the square is not yet uncovered, or if the field itself contains a mine
	 * @throws ArrayIndexOutOfBoundsException if the position is not valid
	 */
	public int countNeighbourMines( int ypos, int xpos );
	
	/**
	 * Guess that a position is free of a mine, and uncover it
	 * @param ypos Y-position of the square in the field
	 * @param xpos X-position of the square in the field
	 * @return true if the square was cleared successfully, false if it contained a mine
	 * @throws ArrayIndexOutOfBoundsException if the position is not valid
	 */
	public boolean uncover( int ypos, int xpos );
	
	/**
	 * We should be able to add observers to the controller
	 * @param o an observer
	 */
	public void addObserver( Observer o );
	
	/**
	 * We should be able to remove observers from the controller
	 * @param o an observer
	 */
	public void deleteObserver( Observer o );
}
