package org.axp.command;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.Test;
import org.junit.Before;

/**
 * Test our command executor thread by sending various commands to Tomcat.
 * 
 * Note that this test acts as a _Client_ for the command pattern, creating the various
 * commands and sending them to the command executor to be queued up.
 */
public class CommandTest {
	private ByteArrayOutputStream outstream = new ByteArrayOutputStream();
	private Tomcat tomcat;
	private CommandExecutorThread executor;
	
	public static void assertTextEquals( String errorMessage, String actualText, String...expectedLines ) {
		StringBuilder expected = new StringBuilder();
		
		for ( String line : expectedLines ) {
			expected.append( line ).append( '\n' );
		}
		
		assertEquals( errorMessage, expected.toString(), actualText );
	}
	
	private void sleep( long time ) {
		try {
			Thread.sleep( time );
		}
		catch ( InterruptedException e ) {
			fail( "Test interrupted" );
		}
	}
	
	private void joinThread( Thread t ) {
		try {
			t.join();
		}
		catch ( InterruptedException e ) {
			fail( "Test interrupted" );
		}
	}
	
	/**
	 * Initialise local objects to print to a particular stream; this allows us to test the output
	 */
	@Before
	public void setup() {
		this.outstream = new ByteArrayOutputStream();
		
		this.tomcat = new Tomcat();
		this.tomcat.setPrintStream( new PrintStream( this.outstream ) );
		
		this.executor = new CommandExecutorThread();
		this.executor.setPrintStream( new PrintStream( this.outstream ) );
	}
	
	/**
	 * Simulate starting a command queue and sending a sequence of commands to it one by one
	 */
	@Test
	public void testCommandQueue() {
		this.executor.start();
		
		this.executor.add( this.tomcat::start );
		sleep( 80 );
		
		this.executor.add( () -> this.tomcat.deploy( "reporting" ) );
		sleep( 80 );
		
		this.executor.add( this.tomcat::restart );
		sleep( 80 );
		
		this.executor.shutdown( false );
		
		joinThread( this.executor );
		
		assertTextEquals( "Output mismatch", this.outstream.toString(),
				"[COMMAND QUEUE]:\tWaiting for more commands",
				"[TOMCAT]:\tStarting...",
				"[TOMCAT]:\tStarted",
				"[TOMCAT]:\tRedeploying webapp reporting...",
				"[TOMCAT]:\tWebapp reporting redeployed",
				"[TOMCAT]:\tStopping...",
				"[TOMCAT]:\tStopped",
				"[TOMCAT]:\tStarting...",
				"[TOMCAT]:\tStarted",
				"[COMMAND QUEUE]:\tFinishing",
				"[COMMAND QUEUE]:\tDone"
				 );
	}
	
	/**
	 * Simulate starting a command queue and giving it a batch of commands to run, each of
	 * which does a single action.
	 */
	@Test
	public void testMultipleCommands() {
		this.executor.start();
		
		this.executor.add(
			this.tomcat::start,
			() -> this.tomcat.deploy( "reporting" ),
			this.tomcat::restart );
		
		this.executor.shutdown( false );
		
		joinThread( this.executor );
		
		assertTextEquals( "Output mismatch", this.outstream.toString(),
				"[COMMAND QUEUE]:\tWaiting for more commands",
				"[TOMCAT]:\tStarting...",
				"[TOMCAT]:\tStarted",
				"[TOMCAT]:\tRedeploying webapp reporting...",
				"[TOMCAT]:\tWebapp reporting redeployed",
				"[TOMCAT]:\tStopping...",
				"[TOMCAT]:\tStopped",
				"[TOMCAT]:\tStarting...",
				"[TOMCAT]:\tStarted",
				"[COMMAND QUEUE]:\tFinishing",
				"[COMMAND QUEUE]:\tDone"
				 );
	}
	
	/**
	 * Simulate starting a command queue and giving it a single command that does multiple
	 * actions in sequence; i.e. a macro command.
	 */
	@Test
	public void testSingleCommandMultipleActions() {
		this.executor.start();
		
		this.executor.add( () -> {
			this.tomcat.start();
			this.tomcat.deploy( "reporting");
			this.tomcat.restart(); } );
		
		this.executor.shutdown( false );
		
		joinThread( this.executor );
		
		assertTextEquals( "Output mismatch", this.outstream.toString(),
				"[COMMAND QUEUE]:\tWaiting for more commands",
				"[TOMCAT]:\tStarting...",
				"[TOMCAT]:\tStarted",
				"[TOMCAT]:\tRedeploying webapp reporting...",
				"[TOMCAT]:\tWebapp reporting redeployed",
				"[TOMCAT]:\tStopping...",
				"[TOMCAT]:\tStopped",
				"[TOMCAT]:\tStarting...",
				"[TOMCAT]:\tStarted",
				"[COMMAND QUEUE]:\tFinishing",
				"[COMMAND QUEUE]:\tDone"
				 );
	}
	
	/**
	 * Simulate starting a command queue and giving it a batch of commands to run, each of
	 * which does a single action. Then do a hard shutdown -- this interrupts it before it
	 * can get round to the later actions.
	 */
	@Test
	public void testHardShutdownMultipleCommands() {
		this.executor.start();
		
		this.executor.add(
				this.tomcat::start,
				() -> this.tomcat.deploy( "reporting" ),
				this.tomcat::restart );
		
		sleep( 2222 );
		this.executor.shutdown( true );
		
		joinThread( this.executor );
		
		assertTextEquals( "Output mismatch", this.outstream.toString(),
				"[COMMAND QUEUE]:\tWaiting for more commands",
				"[TOMCAT]:\tStarting...",
				"[TOMCAT]:\tStarted",
				"[TOMCAT]:\tRedeploying webapp reporting...",
				"[TOMCAT]:\tWebapp reporting redeployed",
				"[COMMAND QUEUE]:\tHard shutdown",
				"[COMMAND QUEUE]:\tFinishing",
				"[COMMAND QUEUE]:\tDone"
				 );
	}
	
	/**
	 * Simulate starting a command queue and giving it a single command that does multiple
	 * actions in sequence. Then do a hard shutdown -- because this is actually just a single
	 * command, it finishes all the actions before shutting down.
	 */
	@Test
	public void testHardShutdownSingleCommandMultipleActions() {
		this.executor.start();
		
		this.executor.add( () -> {
			this.tomcat.start();
			this.tomcat.deploy( "reporting");
			this.tomcat.restart(); } );
		
		sleep( 2222 );
		this.executor.shutdown( true );
		
		joinThread( this.executor );
		
		assertTextEquals( "Output mismatch", this.outstream.toString(),
				"[COMMAND QUEUE]:\tWaiting for more commands",
				"[TOMCAT]:\tStarting...",
				"[TOMCAT]:\tStarted",
				"[TOMCAT]:\tRedeploying webapp reporting...",
				"[TOMCAT]:\tWebapp reporting redeployed",
				"[TOMCAT]:\tStopping...",
				"[TOMCAT]:\tStopped",
				"[TOMCAT]:\tStarting...",
				"[TOMCAT]:\tStarted",
				"[COMMAND QUEUE]:\tHard shutdown",
				"[COMMAND QUEUE]:\tFinishing",
				"[COMMAND QUEUE]:\tDone"
				 );
	}
	
	/**
	 * Show that sending a shutdown signal prevents any further commands being added to the queue.
	 */
	@Test
	public void testAddAfterShutdown() {
		this.executor.start();
		
		assertTrue( "Should be able to add the initial command", this.executor.add( this.tomcat::start ) );
		this.executor.shutdown( false );
		assertFalse( "Shouldn't be able to add commands after shutdown", this.executor.add( this.tomcat::stop ) );
		joinThread( this.executor );
		
		assertTextEquals( "Output mismatch", this.outstream.toString(),
				"[COMMAND QUEUE]:\tWaiting for more commands",
				"[COMMAND QUEUE]:\tCan't add command; already shutting down",
				"[TOMCAT]:\tStarting...",
				"[TOMCAT]:\tStarted",
				"[COMMAND QUEUE]:\tFinishing",
				"[COMMAND QUEUE]:\tDone"
				 );
	}
}
