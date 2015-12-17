package com.spg.chapter1.engines;


public class RocketEngine implements Engine {

	int thrusterFuel = 100;
	
	@Override
	public int dodge() {
		// Really fast, but can only dodge if there's enough thruster fuel to execute a burn in a different direction
		return thrusterFuel--;
	}

}
