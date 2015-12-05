package com.clearscene.Strategy.Buildings;

public class Flat extends Residential {

	public Flat( int bedrooms, String heatingType ) {
		super( 1, 1, heatingType );
	}
	
	public void describe() {
		System.out.println( "This is a Flat" );
		super.describe();
	}
}
