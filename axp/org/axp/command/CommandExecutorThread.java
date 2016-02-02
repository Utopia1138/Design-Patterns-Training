package org.axp.command;

import java.io.PrintStream;
import java.util.LinkedList;

/**
 * The command executor thread maintains a queue of system commands which it will work through. When empty,
 * it will sit waiting for more commands to be added to the queue.
 * 
 * In terms of the command pattern, this is the _Invoker_
 */
public class CommandExecutorThread extends Thread {
	private LinkedList<Command> commandQueue = new LinkedList<Command>();
	private boolean shutdown = false;
	private boolean hardShutdown = false;
	private Process executorProcess = new Process( "Command queue");
	
	/**
	 * Add pending command(s)
	 * @param commands one or more commands to add to the command queue
	 * @return true if the commands were accepted; false if the executor is no longer accepting any commands
	 */
	public boolean add( Command...commands ) {
		if ( this.shutdown ) {
			this.executorProcess.message( "Can't add command; already shutting down" );
			return false;
		}
		
		for ( Command c : commands ) {
			this.commandQueue.offer( c );
		}
		
		return true;
	}
	
	/**
	 * Shut down the command executor
	 * @param hard if true, don't wait for the command queue to be exhausted; shut down as soon as the
	 * current command is finished.
	 */
	public void shutdown( boolean hard ) {
		this.shutdown = true;
		this.hardShutdown = hard;
	}
	
	/**
	 * Execute until the command queue is empty, unless sent a hard shutdown signal
	 */
	private void executeCommands() {
		while ( !this.commandQueue.isEmpty() ) {
			this.commandQueue.poll().execute();
			
			if ( this.hardShutdown ) {
				this.executorProcess.message( "Hard shutdown" );
				return;
			}
		}
	}
	
	/**
	 * Run the command executor thread: run through all commands, then wait for more to be added.
	 * Repeat until we get a shutdown signal. 
	 */
	@Override
	public void run() {
		executeCommands();
		
		while ( !this.shutdown ) {
			this.executorProcess.execute( "Waiting for more commands", 1000 );
			executeCommands();
		}
		
		this.executorProcess.execute( "Finishing", 500 );
		this.executorProcess.message( "Done" );
	}

	/**
	 * Redirect output
	 * @param out
	 */
	public void setPrintStream( PrintStream out ) {
		this.executorProcess.setPrintStream( out );
	}
}
