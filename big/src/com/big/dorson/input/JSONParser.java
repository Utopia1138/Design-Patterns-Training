package com.big.dorson.input;

import com.big.dorson.entity.Transaction;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


public class JSONParser implements Parser {

	@Override
  public Transaction parseRequest( String rawTxn ) {
		System.out.println("Parsing: "+rawTxn);

		Gson gson = new GsonBuilder().create();
		Transaction txn = gson.fromJson(rawTxn, Transaction.class);
    
		return txn;
	  
  }

}
