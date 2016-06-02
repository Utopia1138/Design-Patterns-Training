package org.txr.designpatterns.chapter10;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.hamcrest.core.IsInstanceOf;
import org.junit.Test;

public class InvoiceTest {

	@Test
	public void testInvoiceCreation() {
		Customer customer = new Customer("Mr Smith","Edinburgh", 10);
		Invoice invoice = new Invoice(customer);
		assertEquals(customer, invoice.getCustomer());
		assertTrue((invoice.getStatus() instanceof CreatedStatus));
	}
	
	@Test
	public void testInvoiceVerification() {
		Customer customer = new Customer("Mr Smith","Edinburgh", 10);
		Invoice invoice = new Invoice(customer);
		assertEquals(customer, invoice.getCustomer());
		assertTrue((invoice.getStatus() instanceof CreatedStatus));
		invoice.getStatus().verifyInvoice();
		assertTrue((invoice.getStatus() instanceof VerifiedStatus));
	}
	
	@Test(expected=InvalidInvoiceOperation.class)
	public void testInvoiceCreatedCannotIssue() {
		Customer customer = new Customer("Mr Smith","Edinburgh", 10);
		Invoice invoice = new Invoice(customer);
		assertEquals(customer, invoice.getCustomer());
		assertTrue((invoice.getStatus() instanceof CreatedStatus));
		invoice.getStatus().issueInvoice();
		
	}
	@Test
	public void testFullLifeCycleInvoice (){
		Customer customer = new Customer("Mr Smith","Edinburgh", 10);
		Invoice invoice = new Invoice(customer);
		invoice.setAmount(new BigDecimal(100));
		assertEquals(customer, invoice.getCustomer());
		assertTrue((invoice.getStatus() instanceof CreatedStatus));
		invoice.getStatus().verifyInvoice();
		assertTrue((invoice.getStatus() instanceof VerifiedStatus));
		invoice.getStatus().issueInvoice();
		assertTrue((invoice.getStatus() instanceof IssuedStatus));
		invoice.getStatus().sendReminder();
		assertTrue((invoice.getStatus() instanceof IssuedStatus));
		assertEquals(1,invoice.getNumberOfReminders());
		Payment payment = new Payment(new BigDecimal(100), customer);
		invoice.getStatus().reconcilePayment(payment);
		assertTrue((invoice.getStatus() instanceof ReconciledStatus));
		invoice.getStatus().archiveInvoice();
		assertTrue((invoice.getStatus() instanceof ArchivedStatus));
	}

}
