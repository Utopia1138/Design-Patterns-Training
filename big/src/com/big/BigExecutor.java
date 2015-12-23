package com.big;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import com.big.dorson.authorization.FakeBankAuthorizer;
import com.big.dorson.gateway.Dorson;
import com.big.dorson.gateway.Gateway;
import com.big.dorson.input.JSONParser;
import com.big.dorson.response.JSONResponder;


public class BigExecutor {

	public static void main (String[] args){

		String input = args[0];
		
		System.out.println("EXECUTOR BEGINS: "+input);
		
		String inputAsString = readFile(input);
		
		Gateway gateway = buildGateway();
		
		String response = gateway.process( inputAsString );
		
		System.out.println("EXECUTOR GOT: "+response);
	}

	private static String readFile( String input ) {
		String fileContents = "";
		try {
	     fileContents = new Scanner(new File(input)).useDelimiter("\\Z").next();
    }
    catch ( FileNotFoundException e ) {
	    System.out.println("Couldn't find file ["+input+"]");
    }
		
		return fileContents;
  }

	private static Gateway buildGateway() {
	  
		Dorson dorson = new Dorson();
		
		FakeBankAuthorizer authorizer = new FakeBankAuthorizer();
		dorson.setAuthorizer(authorizer);
		
		JSONParser parser = new JSONParser();
		dorson.setParser( parser );

		JSONResponder responder = new JSONResponder();
		dorson.setResponder( responder );
		
		return dorson;
  }
	
}

