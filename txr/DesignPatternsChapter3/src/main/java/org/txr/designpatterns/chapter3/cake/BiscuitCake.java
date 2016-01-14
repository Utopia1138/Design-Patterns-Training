package org.txr.designpatterns.chapter3.cake;

public class BiscuitCake extends Cake {

	@Override
	public String getDescription() {
		return "Biscuit Cake";
	}

	@Override
	public int cost() {
		return 15;
	}

}
