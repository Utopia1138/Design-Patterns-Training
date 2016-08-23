
package com.spg.chapter12.controller.state;

public enum State {
	SELECTION ("Select"), BUILDER ("Builder"), CAPTAIN ("Captain"), CRAFTSMAN ("Craftsman"), MAYOR ("Mayor"), PROSPECTOR ("Prospector"), SETTLER ("Settler"), TRADER ("Trader");

	private String name;

	private State( String name ) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
}
