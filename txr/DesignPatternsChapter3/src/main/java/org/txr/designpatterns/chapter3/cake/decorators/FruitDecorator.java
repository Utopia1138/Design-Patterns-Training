package org.txr.designpatterns.chapter3.cake.decorators;

import org.txr.designpatterns.chapter3.cake.Cake;
import org.txr.designpatterns.chapter3.cake.CakeDecorator;

public class FruitDecorator extends CakeDecorator {

	public FruitDecorator(Cake cake) {
		super(cake);
	}

	@Override
	public String getDescription() {
		return cake.getDescription() + " and pieces of fruits on top";
	}

	@Override
	public int cost() {
		return cake.cost() +  40;
	}

}
