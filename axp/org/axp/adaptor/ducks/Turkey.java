package org.axp.adaptor.ducks;

/**
 * A related class which does not implement {@link Duck}. Once again based on the examples in the Head First
 * Design Pattern textbook. (Although we don't bother with the separate Turkey interface in our example, because
 * it doesn't gain us anything.)
 */
public class Turkey {
	public String gobble() {
		return "Gobble gobble gobble";
	}
	
	public String fly() {
		return "*Flaps furiously*";
	}
}
