package org.txr.designpatterns.chapter10;

import java.math.BigDecimal;

public class Payment {
	BigDecimal amount;
	Customer originator;
	
	public Payment(BigDecimal amount, Customer originator) {
		super();
		this.amount = amount;
		this.originator = originator;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public Customer getOriginator() {
		return originator;
	}
	public void setOriginator(Customer originator) {
		this.originator = originator;
	}
	
}
