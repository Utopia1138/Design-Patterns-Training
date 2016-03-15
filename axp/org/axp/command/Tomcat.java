package org.axp.command;

/**
 * This is an example of a _Receiver_ for a command. We can tell tomcat to carry out various
 * actions; start, stop, restart, or deploy a webapp. Each command in our system will
 * execute one (or possibly more) of these methods.
 */
public class Tomcat extends Process {
	public Tomcat() {
		super( "Tomcat" );
	}

	private boolean running = false;
	
	/**
	 * Start up Tomcat
	 */
	public void start() {
		if ( this.running ) {
			message( "Already running" );
		}
		else {
			execute( "Starting...", 1000 );
			this.running = true;
			message( "Started" );
		}
	}
	
	/**
	 * Stop Tomcat
	 */
	public void stop() {
		if ( this.running ) {
			execute( "Stopping...", 1000 );
			this.running = false;
			message( "Stopped" );
		}
		else {
			message( "Already stopped" );
		}
	}
	
	/**
	 * Restart Tomcat
	 */
	public void restart() {
		stop();
		start();
	}
	
	/**
	 * (Re)deploy a webapp
	 * @param webapp name of webapp to deploy
	 */
	public void deploy( String webapp ) {
		execute( "Redeploying webapp " + webapp + "...", 500 );
		message( "Webapp " + webapp + " redeployed" );
	}
}
