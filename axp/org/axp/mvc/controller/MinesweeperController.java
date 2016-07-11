package org.axp.mvc.controller;

import java.rmi.Remote;
import java.rmi.RemoteException;

import org.axp.mvc.model.MineSquare;
import org.axp.mvc.model.Minefield;
import org.axp.mvc.rmi.RemoteObserver;

public interface MinesweeperController extends Remote {
	public static final int END_OF_GAME = 1;
	public static final int YOU_STEPPED_ON_A_MINE = 2;
	public static final int YOU_SCORED_A_POINT = 3;

	/**
	 * Count the number of neighbouring mines in a square. Can only be called on uncovered, mine-free squares.
	 * @param ypos Y-position of the square in the field
	 * @param xpos X-position of the square in the field
	 * @return the number of neighbouring squares with a mine
	 * @throws IllegalStateException if the square is not yet uncovered, or if the field itself contains a mine
	 * @throws ArrayIndexOutOfBoundsException if the position is not valid
	 * @throws RemoteException 
	 */
	public int countNeighbourMines( int ypos, int xpos ) throws RemoteException;
	
	/**
	 * Guess that a position is free of a mine, and uncover it
	 * @param client the ui sending the guess
	 * @param ypos Y-position of the square in the field
	 * @param xpos X-position of the square in the field
	 * @throws ArrayIndexOutOfBoundsException if the position is not valid
	 */
	public void uncover( RemoteObserver<MineSquare> client, int ypos, int xpos ) throws RemoteException;
	
	/**
	 * We should be able to add observers to the controller
	 * @param obs an observer
	 */
	public void addObserver( RemoteObserver<MineSquare> obs ) throws RemoteException;
	
	/**
	 * We should be able to remove observers from the controller
	 * @param obs an observer
	 */
	public void deleteObserver( RemoteObserver<MineSquare> obs ) throws RemoteException;

	/**
	 * Get the full state of the minefield at this point
	 * @return a model representing the minefield
	 */
	public Minefield getCurrentFieldState() throws RemoteException;

	/**
	 * What player name does this observer correspond to?
	 * @param obs an observer
	 * @return the player name assigned to this observer
	 */
	public String getPlayerName( RemoteObserver<MineSquare> obs ) throws RemoteException;

	/**
	 * Get the full scores at the end of the game
	 * @return a list of players and their scores
	 */
	public String getFullScores() throws RemoteException;
}
