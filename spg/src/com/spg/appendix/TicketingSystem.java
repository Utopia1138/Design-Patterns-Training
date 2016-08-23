
package com.spg.appendix;

import com.spg.appendix.support.FirstLineSupport;
import com.spg.appendix.support.InternalSupport;
import com.spg.appendix.support.ProductOwner;
import com.spg.appendix.support.SecondLineSupport;
import com.spg.appendix.support.SupportUser;
import com.spg.appendix.support.ThirdLineSupport;

public class TicketingSystem {

	public static void main( String[] args ) {

		// Set up support user chain
		SupportUser chain = new InternalSupport( new FirstLineSupport( new SecondLineSupport( new ThirdLineSupport( new ProductOwner( null ) ) ) ) );

		// Ten tickets are generated
		for ( int i = 0; i < 10; i ++ ) {
			chain.handleTicket( Ticket.generateTicket() );
		}
		
	}

}
