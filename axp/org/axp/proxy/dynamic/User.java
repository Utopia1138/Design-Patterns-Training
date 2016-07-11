package org.axp.proxy.dynamic;

/**
 * Abstract user class with a simple integer privilege level (larger = better)
 */
public abstract class User {
	private int privLevel;
	
	public User( int privLevel ) {
		this.privLevel = privLevel;
	}
	
	public int getPrivilegeLevel() {
		return this.privLevel;
	}
}
