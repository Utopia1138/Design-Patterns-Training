package org.axp.decorator.tweaks;

import java.util.Map;

import org.axp.decorator.MessageFormat;
import org.axp.decorator.model.MessageField;

/**
 * Prepend a message with the number of bytes we expect it to contain, because mumble transmission errors mumble.
 * 
 * @author axp
 */
public class LengthPrepended extends MessageFormatTweak {
	private String formatDigits;

	public LengthPrepended( int numDigits, MessageFormat inner ) {
		super( inner );
		this.formatDigits = "%0" + numDigits + "d";
	}

	@Override
	public String buildMessage( Map<MessageField, String> fields ) {
		String msg = inner.buildMessage( fields );
		return String.format( this.formatDigits, msg.length() ) + msg;
	}
}
