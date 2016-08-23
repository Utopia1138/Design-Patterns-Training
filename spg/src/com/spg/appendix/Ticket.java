
package com.spg.appendix;

/**
 * 
 * A ticket that needs to be addressed.
 *
 */
public class Ticket {
	
	public static int nextId;
	
	static {
		nextId = 1;
	}
	
	/**
	 * The ticket class is its own factory because we're not really bothering with factory patterns in this example. 
	 */
	public static Ticket generateTicket() {
		
		Math.abs( Math.random() - Math.random() );
		int newComplexity = (int) Math.ceil( Math.random() * 10 );
		int newSeverity = (int) Math.ceil( Math.random() * 10 );
		
		System.out.println( "New ticket raised with complexity " + newComplexity + " and severity " + newSeverity  );
		
		return new Ticket(newSeverity, newComplexity, Cause.UNKNOWN);
	}

	private int	id;		// Unique identifier
	private int	severity;		// Priority from 1-10
	private int	complexity;	// How much effort it takes to solve from 1-10
	private Cause	underlyingCause;	// What the actual source of the issue is

	public Ticket( int severity, int complexity, Cause underlyingCause ) {
		this.setId( Ticket.nextId++ );
		this.severity = severity;
		this.complexity = complexity;
		this.setUnderlyingCause( underlyingCause );
	}

	public int getSeverity() {
		return severity;
	}

	public void setSeverity( int severity ) {
		this.severity = severity;
	}

	public int getComplexity() {
		return complexity;
	}

	public void setComplexity( int complexity ) {
		this.complexity = complexity;
	}

	public Cause getUnderlyingCause() {
		return underlyingCause;
	}

	public void setUnderlyingCause( Cause underlyingCause ) {
		this.underlyingCause = underlyingCause;
	}

	public int getId() {
		return id;
	}

	public void setId( int id ) {
		this.id = id;
	}

}
