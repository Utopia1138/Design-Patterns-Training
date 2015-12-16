package org.axp.decorator;

import java.util.Map;

import org.axp.decorator.model.MessageField;
import org.axp.decorator.model.Transaction;

/**
 * This is a class representing how a message format works. First we encode all the various possible fields,
 * then we build them up into an overall message to an acquirer.
 * 
 * TODO: consider how this breaks separation of concerns, then say a Mea Culpa.
 * 
 * @author axp
 */
public abstract class MessageFormat {
	/**
	 * Encode transactional data into a message. 
	 * @param txn data taken from an XML request
	 * @return a request string to the acquirer
	 */
	public String encode( Transaction txn ) {
		return buildMessage( encodeFields( txn ) );
	}

	/**
	 * Encode the various fields of the Transaction object into strings of characters
	 * @param txn data taken from an XML request
	 * @return a map of fields to string data
	 */
	public abstract Map<MessageField, String> encodeFields( Transaction txn );
	
	/**
	 * Build up the message parts into a single message
	 * @param fields a map of fields to string data
	 * @return a request string to the acquirer
	 */
	public abstract String buildMessage( Map<MessageField, String> fields );
}
