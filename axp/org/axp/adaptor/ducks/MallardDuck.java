package org.axp.adaptor.ducks;

/**
 * Concrete class implementing {@link Duck}, again as per the Head First Design Pattern textbook
 */
public class MallardDuck implements Duck {
	@Override
	public String quack() {
		return "Quack, quack!";
	}

	@Override
	public String fly() {
		return "Whee, I'm flying";
	}
}
