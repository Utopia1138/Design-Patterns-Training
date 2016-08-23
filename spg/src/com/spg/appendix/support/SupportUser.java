package com.spg.appendix.support;

import com.spg.appendix.Ticket;

public abstract class SupportUser {

	private SupportUser successor;
	
	public void setSuccessor( SupportUser successor ) {
		this.successor = successor;
	}
	
	public SupportUser getSuccessor() {
		return successor;
	}
	
	public abstract void handleTicket( Ticket ticket );
	
}
