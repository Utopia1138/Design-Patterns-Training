package com.big.dorson.gateway;

import com.big.dorson.authorization.Authorizer;
import com.big.dorson.entity.Transaction;
import com.big.dorson.input.Parser;
import com.big.dorson.response.Responder;


public class Dorson implements Gateway {

	private Authorizer authorizer;
	private Responder responder;
	private Parser parser;
		
	/* (non-Javadoc)
	 * @see com.big.Gateway#process(java.lang.String)
	 */
	@Override
  public String process (String rawRequest){
		Transaction txn = parser.parseRequest(rawRequest);
		authorizer.authorizeTransaction(txn);
		String response = responder.generateResponse(txn);
		return response;
	}

	
  public Authorizer getAuthorizer() {
  	return authorizer;
  }

	
  public void setAuthorizer( Authorizer authorizer ) {
  	this.authorizer = authorizer;
  }

	
  public Responder getResponder() {
  	return responder;
  }

	
  public void setResponder( Responder responder ) {
  	this.responder = responder;
  }

	
  public Parser getParser() {
  	return parser;
  }

	
  public void setParser( Parser parser ) {
  	this.parser = parser;
  }
	
	
}
