package org.txr.designpatterns.chapter10;

public class CreatedStatus extends StatusAdapter {
	
	public CreatedStatus(Invoice invoice) {
		super(invoice);
		// TODO Auto-generated constructor stub
	}

	public void verifyInvoice() {
		System.out.println("verifying invoice");
		invoice.setStatus(new VerifiedStatus(invoice));
	}


}
