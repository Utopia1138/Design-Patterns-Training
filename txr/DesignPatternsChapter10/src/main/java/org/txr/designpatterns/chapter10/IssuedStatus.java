package org.txr.designpatterns.chapter10;

public class IssuedStatus extends StatusAdapter {

	public IssuedStatus(Invoice invoice) {
		super(invoice);
	}

	@Override
	public void reconcilePayment(Payment payment) {
		if (payment.getAmount().equals(invoice.getAmount()) && payment.getOriginator().equals(invoice.getCustomer())) {
			invoice.setStatus(new ReconciledStatus(invoice));
		} else {
			System.out.println("unable to reconcile payment");
		}
	}

	@Override
	public void sendReminder() {
		if (invoice.getNumberOfReminders() < 3) {
			System.out.println("sending reminder for invoice");
			invoice.addReminder();
		} else {
			System.out.println("no luck, let's go bankrupt");
		}
	}
	
	
}
