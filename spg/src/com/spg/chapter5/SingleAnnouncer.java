package com.spg.chapter5;


public class SingleAnnouncer {

	private static SingleAnnouncer announcer;
	private String location;
	
	private SingleAnnouncer() {
		// private
	}
	
	public static SingleAnnouncer getInstance() {
		if ( announcer == null ) {
			announcer = new SingleAnnouncer();
		}
		return announcer;
	}
	
	public void setLocation( String location ) {
		this.location = location;
	}
	
	public String getLocation() {
		if ( location == null || location.length() == 0 ) {
			return "this non-descript void";
		}
		
		return location;
	}
	
	public void speak( String words ) {
		System.out.println( "The Announcer says: "  + words );
	}
}
