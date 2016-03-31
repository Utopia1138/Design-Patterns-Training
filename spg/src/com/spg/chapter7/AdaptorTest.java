package com.spg.chapter7;

import com.spg.chapter7.bank.BankSimulator;
import com.spg.chapter7.dpg.APACSBankModule;
import com.spg.chapter7.dpg.DPGMessageElement;

public class AdaptorTest {

	// We want to send the message to the bank, but oh no! All we have is a DPG message. Let's adapt.
	public static void main( String[] args ) {

		// Build our adaptor
		BankSimulator bank = new BankSimulator();
		APACSBankModule adaptor = new APACSBankModule( bank );
		
		// Txn 1 should be accepted
		DPGMessageElement request1 = new DPGMessageElement( "Request" );
		DPGMessageElement credentials = new DPGMessageElement( "Credentials" );
		credentials.addSubElement( new DPGMessageElement( "vtid", "88100000" ) );
		DPGMessageElement card = new DPGMessageElement( "Card" );
		card.addSubElement( new DPGMessageElement( "pan", "4444333322221111" ) );
		DPGMessageElement txn = new DPGMessageElement( "Txn" );
		txn.addSubElement( new DPGMessageElement( "type" , "auth" ) );
		txn.addSubElement( new DPGMessageElement( "amount" , "200" ) );
		
		request1.addSubElement( credentials );
		request1.addSubElement( card );
		request1.addSubElement( txn );
		
		System.out.println( "Request 1:" );
		System.out.println( request1 );
		
		// Send request 1
		DPGMessageElement response1 = adaptor.sendDPGRequest( request1 );
		System.out.println( "Response 1:" );
		System.out.println( response1 );
		
		//Txn 2 should be declined
		DPGMessageElement request2 = new DPGMessageElement( "Request" );
		txn.addSubElement( new DPGMessageElement( "amount" , "50" ) );
		
		request2.addSubElement( credentials );
		request2.addSubElement( card );
		request2.addSubElement( txn );
		
		System.out.println( "Request 2:" );
		System.out.println( request2 );
		
		// Send request 2
		DPGMessageElement response2 = adaptor.sendDPGRequest( request2 );
		System.out.println( "Response 2:" );
		System.out.println( response2 );

	}

}
