package org.axp.mvc.model;


public interface MinesweeperModel {
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
	 * Reveal a square
	 * @param ypos Y-position of the square in the field
	 * @param xpos X-position of the square in the field
	 * @throws ArrayIndexOutOfBoundsException if the position is not valid
	 */
	public void reveal( int ypos, int xpos );

	/**
	 * Get the square at this position
	 * @param ypos Y-position of the square in the field
	 * @param xpos X-position of the square in the field
	 * @return the square, with associated information
	 */
	public MineSquare squareAt( int ypos, int xpos );
}
