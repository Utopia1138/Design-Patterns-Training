package org.axp.decorator;

import static org.axp.decorator.model.MessageField.*;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import org.axp.decorator.model.MessageField;
import org.axp.decorator.model.Transaction;

/**
 * This message format consists of two parts; a header saying what fields are or are not present, then
 * the rest of the message in the same order.
 * 
 * @author axp
 */
public class Iso3103 extends MessageFormat {
	protected static final SimpleDateFormat YYYYMMDD = new SimpleDateFormat( "yyyyMMdd" );
	protected static final SimpleDateFormat YYMM = new SimpleDateFormat( "yyMM" );
	
	@Override
	public Map<MessageField, String> encodeFields( Transaction txn ) {
		Map<MessageField, String> fields = new HashMap<MessageField, String>();
		
		if ( txn.getCardNumber() != null ) {
			fields.put( CARD_NUMBER, llVar( txn.getCardNumber() ) );
		}
		
		if ( txn.getExpiry() != null ) {
			fields.put( EXPIRY_DATE, YYMM.format( txn.getExpiry() ) );
		}
		
		if ( txn.getType() != null ) {
			fields.put( TRANSACTION_TYPE, txn.getType().name().substring( 0, 1 ) ); // [S|A|C|R]
		}
		
		if ( txn.getAmountPence() != null ) {
			fields.put( AMOUNT_PENCE, String.format( "%012d", txn.getAmountPence() ) );
		}
		
		if ( txn.getDate() != null ) {
			fields.put( TRANSACTION_DATE, YYYYMMDD.format( txn.getDate() ) );
		}
		
		return fields;
	}

	@Override
	public String buildMessage( Map<MessageField, String> fields ) {
		StringBuilder header = new StringBuilder();
		StringBuilder mainPart = new StringBuilder();
		
		for ( MessageField f : new MessageField[] { CARD_NUMBER, EXPIRY_DATE, TRANSACTION_TYPE, 
				AMOUNT_PENCE, TRANSACTION_DATE, CURRENCY } ) {
			
			if ( fields.containsKey( f ) ) {
				header.append( 1 );
				mainPart.append( fields.get( f ) );
			}
			else {
				header.append( 0 );
			}
		}
		
		header.append( "00" ); // Extensible to all of eight fields! We'll never run out!
		
		return header.append( mainPart ).toString();
	}
	
	private String llVar( String data ) {
		return String.format( "%02d", data.length() ) + data;
	}
}
