package com.big.dorson.response;

import com.big.dorson.entity.Transaction;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


public class JSONResponder implements Responder {

	@Override
  public String generateResponse( Transaction txn ) {

		Gson gson = new GsonBuilder().create();
		String response = gson.toJson( txn );
				
		System.out.println("Response generated: "+response);
		
		return response;
  }

}

