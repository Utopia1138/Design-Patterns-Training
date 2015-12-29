package com.big.dorson.authorization;

import com.big.dorson.entity.Transaction;


public class FakeBankAuthorizer implements Authorizer {

	@Override
  public void authorizeTransaction( Transaction txn ) {
	  
		txn.setAuthcode("100000");
		txn.setAuthTimestamp(System.currentTimeMillis());
	  
  }

}
