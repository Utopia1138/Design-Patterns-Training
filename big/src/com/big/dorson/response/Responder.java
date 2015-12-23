package com.big.dorson.response;

import com.big.dorson.entity.Transaction;


public interface Responder {

	String generateResponse( Transaction txn );

}
