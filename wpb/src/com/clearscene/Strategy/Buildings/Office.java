package com.clearscene.Strategy.Buildings;

public class Office extends Business {

	public Office( int floors, int meetingrooms, String heatingType ) {
		super( floors, meetingrooms, heatingType );
	}
	
	public void describe() {
		System.out.println( "This is an office" );
		super.describe();
	}
}
