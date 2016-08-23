package com.spg.appendix.support;

import com.spg.appendix.Cause;
import com.spg.appendix.Ticket;

/**
 * 
 * aka Dev, with the knoweldge to understand the underlying functionality and the power to fix code problems
 *
 */
public class ThirdLineSupport extends SupportUser {

	public ThirdLineSupport( SupportUser successor ) {
		setSuccessor( successor );
	}

	@Override
	public void handleTicket( Ticket ticket ) {
		
		// -1 modifier for third-line support - they build the system but might not have worked on this part of it
		double roll = Math.ceil( Math.random() * 10 - 1 );
		
		// If it's a code issue Dev get bonus as some of the work's already been done
		if ( ticket.getUnderlyingCause() == Cause.CODE ) {
			roll += 2;
		}
		System.out.println( "Third line support roll " + roll + " on their skill check" );
		
		// At this point, if Dev can't solve it there must be something wrong with the requirements
		 if ( ticket.getComplexity() < roll ) {
			 System.out.println( "Third-line solve the problem. Yay!" );
		 }
		 else {
			 ticket.setUnderlyingCause( Cause.REQUIREMENT );
			 System.out.println( "Third-line raise issue with Product to create a change request to fix the problem" );
			 getSuccessor().handleTicket( ticket );
		 }
		
	}

}
