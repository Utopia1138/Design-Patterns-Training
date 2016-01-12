package org.txr.designpatterns.chapter3.cake;

public class SpongeCake extends Cake {

	@Override
	public String getDescription() {
		return "spongeCake";
	}

	@Override
	public int cost() {
		return 10;
	}

}
