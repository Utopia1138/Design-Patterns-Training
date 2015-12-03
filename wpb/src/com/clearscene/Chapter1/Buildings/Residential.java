package com.clearscene.Chapter1.Buildings;

public abstract class Residential extends Building{

	private int bedrooms;

	public Residential( int floors, int bedrooms, String heatingType ) {
		super( floors, heatingType );
		setBedrooms( bedrooms );
	}

	public int getBedrooms() {
		return bedrooms;
	}

	public void setBedrooms(int bedrooms) {
		this.bedrooms = bedrooms;
	}
	
	public void describe() {
		System.out.println( "We have " + bedrooms + " bedrooms." );
		super.describe();
	}
}
