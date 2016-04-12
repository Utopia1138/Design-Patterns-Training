package com.spg.chapter7.bank;


public class BankSimulator {

	public APACSResponse sendAuth( APACSRequest request ) {
		
		APACSResponse response = new APACSResponse();
		response.setTid( request.getTid() );
		
		if ( Integer.parseInt( request.getAmount() ) > 100 ) {
			response.setAuthcode( request.getAmount() );
			response.setMessage( "ACCEPTED" );
		}
		else {
			response.setAuthcode( "      " );
			response.setMessage( "DECLINED" );
		}
		
		return response;
	}
	
}
