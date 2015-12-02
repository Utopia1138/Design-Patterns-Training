package com.clearscene.Chapter1.Buildings;

public class House extends Building{

	private int bedrooms;

	public House( int floors, int bedrooms, String heatingType ) {
		super( floors, heatingType );
		setBedrooms( bedrooms );
	}

	public int getBedrooms() {
		return bedrooms;
	}

	public void setBedrooms(int badrooms) {
		this.bedrooms = badrooms;
	}
	
	public void describe() {
		System.out.println( "This is a House with " + bedrooms + " bedrooms" );
		super.describe();
	}
}
