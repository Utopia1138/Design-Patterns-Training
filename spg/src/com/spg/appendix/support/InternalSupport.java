package com.spg.appendix.support;

import com.spg.appendix.Cause;
import com.spg.appendix.Ticket;

/**
 * 
 * The user's own internal support team.
 *
 */
public class InternalSupport extends SupportUser {

	public InternalSupport( SupportUser successor ) {
		setSuccessor( successor );
	}

	@Override
	public void handleTicket( Ticket ticket ) {
		// Too severe, escalate immediately
		if ( ticket.getSeverity() > 7 ) {
			System.out.println( "Internal support passes high-priority ticket to first-line support" );
			getSuccessor().handleTicket( ticket );
			return;
		}
		
		// -5 modifier for internal support who know nothing about the system
		double roll = Math.ceil( Math.random() * 10 - 5 );
		System.out.println( "Internal support roll " + roll + " on their skill check" );

		// If the complexity is below a randomly-generated value we discover the user just messed up
		if ( ticket.getComplexity() < roll ) {
			ticket.setUnderlyingCause( Cause.PEBKAC );
			System.out.println( "Internal support applied percussive maintenace to the PEBKAC" );
		}
		else {
			System.out.println( "Internal support passes ticket to first-line support" );
			getSuccessor().handleTicket( ticket );
			return;
		}

	}

}
