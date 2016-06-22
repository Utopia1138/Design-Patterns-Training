package com.spg.chapter7.dpg;

import com.spg.chapter7.bank.APACSRequest;
import com.spg.chapter7.bank.APACSResponse;
import com.spg.chapter7.bank.BankSimulator;

public class APACSBankModule implements BankModule {

	private BankSimulator bank;
	
	public APACSBankModule( BankSimulator bank ) {
		this.bank = bank;
	}
	
	
	@Override
	public DPGMessageElement sendDPGRequest( DPGMessageElement request ) {

		String vtid = request.getSubElement( "Credentials" ).getSubElement( "vtid" ).getValue();
		String amount = request.getSubElement( "Txn" ).getSubElement( "amount" ).getValue();
		String pan = request.getSubElement( "Card" ).getSubElement( "pan" ).getValue();
		
		APACSRequest bankRequest = new APACSRequest();
		// TID is vTID minus first 2 digits because because
		bankRequest.setTid( vtid.substring( 2, 8 ) );
		bankRequest.setAmount( amount );
		bankRequest.setPan( pan );
		
		APACSResponse bankResponse = bank.sendAuth( bankRequest );
		
		// Build the response
		DPGMessageElement response = new DPGMessageElement( "Response" );
		response.addSubElement( new DPGMessageElement( "tid", bankResponse.getTid() ) );
		response.addSubElement( new DPGMessageElement( "authcode", bankResponse.getAuthcode() ) );
		response.addSubElement( new DPGMessageElement( "message", bankResponse.getMessage() ) );
		
		return response;
	}

}
