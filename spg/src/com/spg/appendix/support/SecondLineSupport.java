package com.spg.appendix.support;

import com.spg.appendix.Cause;
import com.spg.appendix.Ticket;

/**
 * 
 * If first-line can't handle it, it comes to people with slightly more knowledge and visibility.
 *
 */
public class SecondLineSupport extends SupportUser {

	public SecondLineSupport( SupportUser successor ) {
		setSuccessor( successor );
	}

	@Override
	public void handleTicket( Ticket ticket ) {
		// If it's a code issue it gets bumped straight to Dev
		if ( ticket.getUnderlyingCause() == Cause.CODE ) {
			System.out.println( "Second-line support passes Code ticket to third-line support (dev)" );
			getSuccessor().handleTicket( ticket );
			return;
		}
		
		// -2 modifier for second-line support who can access parts of the system but don't necessarily know how it works
		double roll = Math.ceil( Math.random() * 10 - 2 );
		System.out.println( "Second line support roll " + roll + " on their skill check" );
		
		if ( ticket.getComplexity() < roll - 2 ) {
			ticket.setUnderlyingCause( Cause.CODE );
			System.out.println( "Second-line identify a code issue and pass it on to Dev" );
			getSuccessor().handleTicket( ticket );
		}
		else if ( ticket.getComplexity() < roll - 1 ) {
			ticket.setUnderlyingCause( Cause.CONFIG );
			System.out.println( "Second-line spot the configuration problem and resolve it" );
		}
		else if ( ticket.getComplexity() < roll ) {
			System.out.println( "Second-line tell customers to apply percussive maintenace to the PEBKAC" );
		}
		else {
			System.out.println( "Second-line support passes ticket to third-line support" );
			getSuccessor().handleTicket( ticket );
		}
	}

}
