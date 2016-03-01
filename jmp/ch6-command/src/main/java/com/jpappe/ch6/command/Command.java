package com.jpappe.ch6.command;

/**
 * Encapsulates an operation that is performed in the game.
 * 
 * @author Jacob Pappe
 *
 */
public interface Command {

	public void execute();
	
	public void undo();
	
}
