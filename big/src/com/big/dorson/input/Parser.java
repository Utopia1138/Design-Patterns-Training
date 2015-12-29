package com.big.dorson.input;

import com.big.dorson.entity.Transaction;


public interface Parser {

	Transaction parseRequest( String rawTxn );

}
