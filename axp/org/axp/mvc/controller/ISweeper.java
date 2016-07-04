package org.axp.mvc.controller;

import java.rmi.Remote;
import java.rmi.RemoteException;

import org.axp.mvc.model.MineSquare;
import org.axp.mvc.rmi.RemoteObserver;

public interface ISweeper extends Remote {
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
	 * @param ypos Y-position of the square in the field
	 * @param xpos X-position of the square in the field
	 * @return true if the square was cleared successfully, false if it contained a mine
	 * @throws ArrayIndexOutOfBoundsException if the position is not valid
	 */
	public boolean uncover( int ypos, int xpos ) throws RemoteException;
	
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
}
