package org.axp.command;

import java.io.PrintStream;

/**
 * Just a model of a system process; we can print messages to output and simulate doing something
 * with {@link #execute(String, long)}.
 */
public class Process {
	private String name;
	protected PrintStream out = System.out;
	
	public Process( String name ) {
		this.name = name.toUpperCase();
	}
	
	public void setPrintStream( PrintStream out ) {
		this.out = out;
	}
	
	public void message( String message ) {
		this.out.printf( "[%s]:\t%s\n", this.name, message );
	}
	
	public void execute( String message, long howLong ) {
		message( message );
		
		try {
			Thread.sleep( howLong );
		}
		catch ( InterruptedException e ) {
			/* Continue with execution */
		}
	}
}
