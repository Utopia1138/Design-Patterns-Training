package com.spg.appendix.support;

import com.spg.appendix.Cause;
import com.spg.appendix.Ticket;

/**
 * 
 * First people to receive external support tickets.
 *
 */
public class FirstLineSupport extends SupportUser {

	public FirstLineSupport( SupportUser successor ) {
		setSuccessor( successor );
	}

	@Override
	public void handleTicket( Ticket ticket ) {
		// High severity gets bumped straight to second-line support
		if ( ticket.getSeverity() > 7 ) {
			System.out.println( "First-line support passes high-priority ticket to second-line support" );
			getSuccessor().handleTicket( ticket );
			return;
		}
		
		// -4 modifier for first-line support who know next to nothing about the system
		double roll = Math.ceil( Math.random() * 10 - 4 );
		System.out.println( "First line support roll " + roll + " on their skill check" );
		
		if ( ticket.getComplexity() < roll - 3 ) {
			ticket.setUnderlyingCause( Cause.CODE );
			System.out.println( "First-line somehow worked out it was a code issue and pass it on" );
			getSuccessor().handleTicket( ticket );
		}
		else if ( ticket.getComplexity() < roll - 1 ) {
			ticket.setUnderlyingCause( Cause.CONFIG );
			System.out.println( "First-line spot the configuration problem and resolve it" );
		}
		else if ( ticket.getComplexity() < roll ) {
			System.out.println( "First-line tell customers to apply percussive maintenace to the PEBKAC" );
		}
		else {
			System.out.println( "First-line support passes ticket to second-line support" );
			getSuccessor().handleTicket( ticket );
		}
	}

}
