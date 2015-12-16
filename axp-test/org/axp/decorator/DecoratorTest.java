package org.axp.decorator;

import static org.junit.Assert.*;

import java.util.Calendar;

import org.axp.decorator.model.MessageField;
import org.axp.decorator.model.Transaction;
import org.axp.decorator.model.TransactionType;
import org.axp.decorator.tweaks.FixedFieldData;
import org.axp.decorator.tweaks.LengthPrepended;
import org.axp.decorator.tweaks.SubpenceInAmount;
import org.junit.Test;

/**
 * This shows how message generation can be built up using a decorator pattern.
 * 
 * @author axp
 */
public class DecoratorTest {

	@Test
	public void testAllTweaks() {
		MessageFormat fooBankMsgFmt = new Iso3103();
		fooBankMsgFmt = new SubpenceInAmount( fooBankMsgFmt );
		fooBankMsgFmt = new FixedFieldData( MessageField.CURRENCY, "GBP", fooBankMsgFmt );
		fooBankMsgFmt = new LengthPrepended( 3, fooBankMsgFmt );
		
		Transaction txn = new Transaction()
			.setType( TransactionType.SETUP )
			.setDate( 2016, Calendar.JANUARY, 6 )
			.setCardNumber( "4444333322221111" )
			.setAmountPence( 1000L )
			.setExpiry( 2018, Calendar.DECEMBER );
		
		String message = fooBankMsgFmt.encode( txn );
		assertEquals( "Foo Bank Message Format", "054111111001644443333222211111812S00000001000020160106GBP", message );
	}
}
