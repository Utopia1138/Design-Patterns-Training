package com.spg.appendix.support;

import com.spg.appendix.Ticket;

/**
 * 
 * If a ticket reaches Product, it should have been analyzed in enough depth to allow a change request to be raised.
 *
 */
public class ProductOwner extends SupportUser {

	public ProductOwner( SupportUser successor ) {
		setSuccessor( successor );
	}

	@Override
	public void handleTicket( Ticket ticket ) {
		System.out.println( "A change request has been raised for ticket " + ticket.getId() );

	}

}
