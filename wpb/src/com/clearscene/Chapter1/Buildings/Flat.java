package com.clearscene.Chapter1.Buildings;

public class Flat extends Building{

	private int badrooms;

	public Flat( int bedrooms, String heatingType ) {
		super( 1, heatingType );
		setBadrooms( bedrooms );
	}

	public int getBadrooms() {
		return badrooms;
	}

	public void setBadrooms(int badrooms) {
		this.badrooms = badrooms;
	}
	
	public void describe() {
		System.out.println( "This is a Flat" );
		super.describe();
	}
}
