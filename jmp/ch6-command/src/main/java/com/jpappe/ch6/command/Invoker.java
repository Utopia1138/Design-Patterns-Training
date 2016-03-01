package com.jpappe.ch6.command;

/**
 * The invoker holds a command and at some point asks
 * it to execute.
 * 
 * @author Jacob Pappe
 *
 */
public interface Invoker<T extends Command> {

	void setCommand(T c);
}
