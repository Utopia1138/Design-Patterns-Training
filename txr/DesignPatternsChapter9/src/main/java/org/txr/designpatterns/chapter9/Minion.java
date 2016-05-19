package org.txr.designpatterns.chapter9;

public class Minion extends Employee {

	public Minion(String name, int id) {
		super(name, id);
	}

	@Override
	public String getOrganigram() {
		return super.getOrganigram() +
		"manages no-one\n";		
	}
	

}
