package com.spg.chapter1.engines;


public class IonEngine implements Engine {

	@Override
	public int dodge() {
		// Slow but steady, but struggles to dodge anything
		return 5;
	}

}
