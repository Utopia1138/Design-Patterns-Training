package org.axp.mvc.model;

import java.awt.Dimension;
import java.util.Observer;

public interface IMinefield {
	/**
	 * @return the dimensions of this minefield
	 */
	public Dimension getDimensions();
	
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
	 * Is the minefield completely cleared? That is, are all non-mine squares uncovered?
	 * @return true if the field is cleared, false if there are still squares to clear
	 */
	public boolean isCleared();
	
	/**
	 * We should be able to add observers to the model
	 * @param o an observer
	 */
	public void addObserver( Observer o );
	
	/**
	 * We should be able to remove observers from the model
	 * @param o an observer
	 */
	public void deleteObserver( Observer o );

	/**
	 * Reveal a square
	 * @param ypos Y-position of the square in the field
	 * @param xpos X-position of the square in the field
	 * @throws ArrayIndexOutOfBoundsException if the position is not valid
	 */
	public void reveal( int ypos, int xpos );

	/**
	 * Has the square got a mine in it?
	 * @param ypos Y-position of the square in the field
	 * @param xpos X-position of the square in the field
	 * @throws ArrayIndexOutOfBoundsException if the position is not valid
	 * @return true if the field contains a mine, false otherwise
	 */
	public boolean hasMine( int ypos, int xpos );
}
