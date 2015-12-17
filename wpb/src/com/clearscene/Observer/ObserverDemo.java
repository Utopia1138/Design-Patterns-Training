package com.clearscene.Observer;

//
// A Cell heat exchange siumulation
//
public class ObserverDemo {
	
	public static void main( String[] args ) {
		
		World sim = new World();
		
		sim.initialise();
		
		while( true ) {
			sim.play();
		}
		
	}

}
