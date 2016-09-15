package org.txr.designpatterns.chapter10;

public class StatusAdapter implements InvoiceStatus {
	protected Invoice invoice;
	
	public StatusAdapter(Invoice invoice) {
		super();
		this.invoice = invoice;
	}

	protected void unauthorizedOperation(String operation) {
		System.out.println("Operation " + operation + " is not permitted for " + this.getClass().getSimpleName());
	}
	public void issueInvoice() {
		unauthorizedOperation("Issuing invoice");
		throw new InvalidInvoiceOperation();

	}

	public void verifyInvoice() {
		unauthorizedOperation("Verifying invoice");
		throw new InvalidInvoiceOperation();

	}

	public void reconcilePayment(Payment payment) {
		unauthorizedOperation("Reconciling invoice");
		throw new InvalidInvoiceOperation();

	}

	public void sendReminder() {
		unauthorizedOperation("sending reminder for invoice");
		throw new InvalidInvoiceOperation();

	}

	public void archiveInvoice() {
		unauthorizedOperation("archiving invoice");
		throw new InvalidInvoiceOperation();

	}

}
