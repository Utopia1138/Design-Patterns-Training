package org.txr.designpatterns.chapter10;

public interface InvoiceStatus {

	public void issueInvoice( );
	public void verifyInvoice( );
	public void reconcilePayment(Payment payment);
	public void sendReminder ();
	public void archiveInvoice();
}
