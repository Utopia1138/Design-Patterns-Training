package org.axp.decorator.tweaks;

import java.util.Map;

import org.axp.decorator.MessageFormat;
import org.axp.decorator.model.MessageField;
import org.axp.decorator.model.Transaction;

/**
 * This acquirer for some reason wants fractions of pence. Ok, whatever.
 * 
 * @author axp
 */
public class SubpenceInAmount extends MessageFormatTweak {
	public SubpenceInAmount( MessageFormat inner ) {
		super( inner );
	}

	@Override
	public Map<MessageField, String> encodeFields( Transaction txn ) {
		Map<MessageField, String> fields = inner.encodeFields( txn );
		
		if ( fields.containsKey( MessageField.AMOUNT_PENCE ) ) {
			String amountPence = fields.get( MessageField.AMOUNT_PENCE );
			amountPence = amountPence.substring( 1 ) + '0'; // Take a zero off the front and add it to the end
			fields.put( MessageField.AMOUNT_PENCE, amountPence );
		}
		
		return fields;
	}
}
