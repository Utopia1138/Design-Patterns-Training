package org.axp.decorator.tweaks;

import java.util.Map;

import org.axp.decorator.MessageFormat;
import org.axp.decorator.model.MessageField;
import org.axp.decorator.model.Transaction;

/**
 * Always have a fixed value for some particular field
 * 
 * @author axp
 */
public class FixedFieldData extends MessageFormatTweak {
	private MessageField field;
	private String value;
	
	public FixedFieldData( MessageField field, String value, MessageFormat inner ) {
		super( inner );
		this.field = field;
		this.value = value;
	}

	@Override
	public Map<MessageField, String> encodeFields( Transaction txn ) {
		Map<MessageField, String> fields = inner.encodeFields( txn );
		
		fields.put( this.field, this.value );
		return fields;
	}
}
