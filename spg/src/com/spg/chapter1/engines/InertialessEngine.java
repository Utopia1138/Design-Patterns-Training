package com.spg.chapter1.engines;


public class InertialessEngine implements Engine {

	@Override
	public int getSpeed() {
		// If we're breaking physics anyway...
		return 270000000;
	}

}
