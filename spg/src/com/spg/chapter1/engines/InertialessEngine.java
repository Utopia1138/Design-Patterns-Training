package com.spg.chapter1.engines;


public class InertialessEngine implements Engine {

	@Override
	public int dodge() {
		// If we're breaking physics anyway...it can turn on a dime so it can dodge easily
		return 95;
	}

}
