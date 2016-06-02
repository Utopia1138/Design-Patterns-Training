package org.txr.designpatterns.chapter10;

public class ReconciledStatus extends StatusAdapter {

	public ReconciledStatus(Invoice invoice) {
		super(invoice);
	}

	@Override
	public void archiveInvoice() {
		System.out.println("archiving invoice");
		invoice.setStatus(new ArchivedStatus(invoice));
	}

}
