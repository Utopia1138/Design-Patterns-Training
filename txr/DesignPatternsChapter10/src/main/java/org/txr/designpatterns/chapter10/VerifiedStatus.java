package org.txr.designpatterns.chapter10;

public class VerifiedStatus extends StatusAdapter{
	
	public VerifiedStatus(Invoice invoice) {
		super(invoice);
	}

	public void issueInvoice() {
		System.out.println("issuing invoice");
		invoice.setStatus(new IssuedStatus(invoice));
	}

	
	
}
