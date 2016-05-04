package org.axp.template;

import static org.junit.Assert.*;

import javax.xml.parsers.ParserConfigurationException;

import org.axp.template.xml.XMLBuilder;
import org.axp.template.xml.XMLQuery;
import org.junit.Test;
import org.w3c.dom.Document;

public class TestNoopAcquirer {
	private NoopAcquirer acq = new NoopAcquirer();

	@Test
	public void test() throws ParserConfigurationException {
		Document request = new XMLBuilder().start( "Request" ).
			start( "Authentication" ).
				addChild( "terminal", "12462412" ).
				addChild( "password", "somepassword" ).
			finish().
			start( "Transaction" ).
				start( "Details" ).
					addChild( "reference", "100000" ).
					addChild( "amount", "1000.00", "currency", "GBP" ).
					addChild( "txndate", "130904" ).
					addChild( "capturemethod", "ecommerce" ).
				finish().
				start( "Card" ).
					addChild( "cardnum", "4444333322221111" ).
					addChild( "expirydate", "12/38" ).
				finish().
				addChild( "method", "auth" ).
			finish().
		finish().getDocument();
		
		Document response = acq.processTransaction( request );
		
		assertEquals( "ACCEPTED", XMLQuery.get( "/Response/reason", response ) );
		assertEquals( "1", XMLQuery.get( "/Response/code", response ) );
		assertEquals( "87654321", XMLQuery.get( "/Response/reference", response ) );
	}
}
