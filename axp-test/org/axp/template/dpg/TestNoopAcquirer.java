package org.axp.template.dpg;

import static org.junit.Assert.*;

import javax.xml.parsers.ParserConfigurationException;

import org.axp.template.dpg.NoopAcquirer;
import org.axp.template.dpg.XMLBuilder;
import org.axp.template.dpg.XMLQuery;
import org.junit.Test;
import org.w3c.dom.Document;

public class TestNoopAcquirer {
	private NoopAcquirer acq = new NoopAcquirer();

	@Test
	public void test() throws ParserConfigurationException {
		Document request = new XMLBuilder().start( "Request" ).
			start( "Authentication" ).
				addChild( "client", "8801100" ).
				addChild( "password", "fred" ).
			finish().
			start( "Transaction" ).
				start( "TxnDetails" ).
					addChild( "merchantreference", "100000" ).
					addChild( "amount", "1000.00", "currency", "GBP" ).
					addChild( "txndate", "130904" ).
					addChild( "capturemethod", "ecomm" ).
				finish().
				start( "CardTxn" ).
					start( "Card" ).
						addChild( "pan", "4444333322221111" ).
						addChild( "expirydate", "12/38" ).
					finish().
					addChild( "method", "auth" ).
				finish().
			finish().
		finish().getDocument();
		
		Document response = acq.processTransaction( request );
		
		assertEquals( "ACCEPTED", XMLQuery.get( "/Response/reason", response ) );
		assertEquals( "1", XMLQuery.get( "/Response/status", response ) );
		assertEquals( "123456789", XMLQuery.get( "/Response/datacash_reference", response ) );
		assertEquals( "54321", XMLQuery.get( "/Response/merchantreference", response ) );
	}
}
