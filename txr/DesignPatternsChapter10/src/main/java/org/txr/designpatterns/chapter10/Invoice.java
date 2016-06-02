package org.txr.designpatterns.chapter10;

import java.math.BigDecimal;

public class Invoice {

	private InvoiceStatus status;
	private Customer customer;
	private BigDecimal amount;
	private int numberOfReminders;
	
	public Invoice(Customer customer) {
		this.customer = customer;
		this.status = new CreatedStatus(this);
	}

	public InvoiceStatus getStatus() {
		return status;
	}

	public void setStatus(InvoiceStatus status) {
		this.status = status;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public int getNumberOfReminders() {
		return numberOfReminders;
	}

	public void addReminder() {
		this.numberOfReminders++;
	}
	
}
