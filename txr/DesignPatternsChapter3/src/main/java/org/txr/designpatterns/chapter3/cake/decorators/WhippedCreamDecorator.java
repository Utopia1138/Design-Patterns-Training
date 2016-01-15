package org.txr.designpatterns.chapter3.cake.decorators;

import org.txr.designpatterns.chapter3.cake.Cake;
import org.txr.designpatterns.chapter3.cake.CakeDecorator;

public class WhippedCreamDecorator extends CakeDecorator {

	public WhippedCreamDecorator(Cake cake) {
		super(cake);
	}

	@Override
	public String getDescription() {
		return cake.getDescription() + " with Wipped Cream";
	}

	@Override
	public int cost() {
		return cake.cost() + 20;
	
	}
}
