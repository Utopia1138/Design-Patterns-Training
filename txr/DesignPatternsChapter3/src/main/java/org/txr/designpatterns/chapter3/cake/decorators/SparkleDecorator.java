package org.txr.designpatterns.chapter3.cake.decorators;

import org.txr.designpatterns.chapter3.cake.Cake;
import org.txr.designpatterns.chapter3.cake.CakeDecorator;

public class SparkleDecorator extends CakeDecorator {

	public SparkleDecorator(Cake cake) {
		super(cake);
	}

	@Override
	public String getDescription() {
		return cake.getDescription() + "\n" + " some sparkles";
	}

	@Override
	public int cost() {
		return 30;
	}

}
