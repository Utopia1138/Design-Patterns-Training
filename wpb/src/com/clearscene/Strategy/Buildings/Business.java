package com.clearscene.Strategy.Buildings;

public abstract class Business extends Building{

	private int meetingrooms;

	public Business( int floors, int meetingrooms, String heatingType ) {
		super( floors, heatingType );
		setMeetingrooms( meetingrooms );
	}

	public int getMeetingrooms() {
		return meetingrooms;
	}

	public void setMeetingrooms(int meetingrooms) {
		this.meetingrooms = meetingrooms;
	}
	
	public void describe() {
		System.out.println( "We have " + meetingrooms + " meeting rooms." );
		super.describe();
	}
}
