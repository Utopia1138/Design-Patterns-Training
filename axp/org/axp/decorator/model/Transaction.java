package org.axp.decorator.model;

import java.util.Calendar;
import java.util.Date;

/**
 * Just some transaction data. This was probably automatically populated from an XML-parsing library,
 * because we're not masochists, are we?
 * 
 * TODO: maybe we want to work in currencies other than GBP at some point?
 * 
 * @author axp
 */
public class Transaction {
	private TransactionType type;
	private String cardNumber;
	private Long amountPence;
	private Date transactionDate;
	private Date expiryDate;

	public Transaction setType( TransactionType type ) {
		this.type = type;
		return this;
	}

	public TransactionType getType() {
		return this.type;
	}

	public String getCardNumber() {
		return this.cardNumber;
	}

	public Transaction setCardNumber( String cardNumber ) {
		this.cardNumber = cardNumber;
		return this;
	}

	public Long getAmountPence() {
		return this.amountPence;
	}

	public Transaction setAmountPence( Long amountPence ) {
		this.amountPence = amountPence;
		return this;
	}

	public Date getDate() {
		return this.transactionDate;
	}

	public Transaction setDate( int year, int month, int date ) {
		Calendar c = Calendar.getInstance();
		c.set( year, month, date, 0, 0, 0 );
		this.transactionDate = c.getTime();
		return this;
	}
	
	public Date getExpiry() {
		return this.expiryDate;
	}
	
	public Transaction setExpiry( int year, int month ) {
		Calendar c = Calendar.getInstance();
		c.set( year, month, 1, 0, 0, 0 );
		this.expiryDate = c.getTime();
		return this;
	}
}
