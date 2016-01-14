package org.txr.designpatterns.chapter3.cake.decorators;

import org.txr.designpatterns.chapter3.cake.Cake;
import org.txr.designpatterns.chapter3.cake.CakeDecorator;

public class SugarIcingDecorator extends CakeDecorator {
	
	public SugarIcingDecorator(Cake cake) {
		super(cake);
		// TODO Auto-generated constructor stub
	}

	
	@Override
	public String getDescription() {
		return cake.getDescription() + "\n" + "with Icing";
	}

	@Override
	public int cost() {
		return cake.cost() + 12;
	}

}
