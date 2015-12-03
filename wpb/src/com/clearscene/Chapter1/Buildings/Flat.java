package com.clearscene.Chapter1.Buildings;

public class Flat extends Residential {

	public Flat( int bedrooms, String heatingType ) {
		super( 1, 1, heatingType );
	}
	
	public void describe() {
		System.out.println( "This is a Flat" );
		super.describe();
	}
}
