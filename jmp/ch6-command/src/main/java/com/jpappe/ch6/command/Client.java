package com.jpappe.ch6.command;

/**
 * The Client is responsible for creating a concrete 
 * Command and setting its receiver
 * 
 * @author Jacob Pappe
 *
 */
public interface Client<T extends Command> {

	public T getCommand();
	
}
