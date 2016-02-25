package org.txr.designpatterns.chapter6.candidates;

public class Politician {
	
	public void quote(String message) {
		System.out.println(this + ":\n " + message+"\n");
	}
	public String toString() {
		return this.getClass().getSimpleName();
	}
}
