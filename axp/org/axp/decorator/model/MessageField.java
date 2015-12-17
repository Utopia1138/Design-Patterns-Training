package org.axp.decorator.model;

/**
 * Various fields that a message format might support. How many could there possibly be?
 * 
 * @author axp
 */
public enum MessageField {
	TRANSACTION_TYPE,
	CARD_NUMBER,
	AMOUNT_PENCE,
	TRANSACTION_DATE,
	EXPIRY_DATE,
	CURRENCY;
}
