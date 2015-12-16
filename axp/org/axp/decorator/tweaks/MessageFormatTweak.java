package org.axp.decorator.tweaks;

import java.util.Map;

import org.axp.decorator.MessageFormat;
import org.axp.decorator.model.MessageField;
import org.axp.decorator.model.Transaction;

/**
 * This is a decorator over MessageFormat; by default it just delegates all methods to its inner
 * format. Subclass it and override one or more of the methods.
 * 
 * @author axp
 */
public abstract class MessageFormatTweak extends MessageFormat {
	protected final MessageFormat inner;
	
	public MessageFormatTweak( MessageFormat inner ) {
		this.inner = inner;
	}

	@Override
	public Map<MessageField, String> encodeFields( Transaction txn ) {
		return inner.encodeFields( txn );
	}

	@Override
	public String buildMessage( Map<MessageField, String> fields ) {
		return inner.buildMessage( fields );
	}
}
