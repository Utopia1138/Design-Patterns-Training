package com.clearscene.Chapter1.Buildings;

public class House extends Residential{

	public House( int floors, int bedrooms, String heatingType ) {
		super( floors, bedrooms, heatingType );
	}
	
	public void describe() {
		System.out.println( "This is a House" );
		super.describe();
	}
}
