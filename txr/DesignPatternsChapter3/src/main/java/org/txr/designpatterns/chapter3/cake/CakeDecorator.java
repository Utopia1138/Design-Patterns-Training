package org.txr.designpatterns.chapter3.cake;

public abstract class CakeDecorator extends Cake {
	protected Cake cake;
	public CakeDecorator (Cake cake) {
		this.cake = cake;
	}
	public abstract String getDescription(); 

	public abstract int cost() ;

}
