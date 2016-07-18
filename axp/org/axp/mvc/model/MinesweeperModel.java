package org.axp.mvc.model;

import java.util.List;


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

	/**
	 * Add a new player to the game
	 * @return
	 */
	public Player newPlayer();
	
	/**
	 * Increase the score of a given player
	 * @param p an existing player
	 */
	public void addPoint( Player p );
	
	/**
	 * Get the final list of players and scores
	 * @return
	 */
	public List<Player> getScores();

	/**
	 * Get the current field
	 * @return
	 */
	public Minefield getField();

	/**
	 * Mark a player as dead
	 * @param p an existing player
	 */
	public void killPlayer( Player p );
}
