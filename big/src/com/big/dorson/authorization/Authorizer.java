package com.big.dorson.authorization;

import com.big.dorson.entity.Transaction;


public interface Authorizer {

	void authorizeTransaction( Transaction txn );

}
