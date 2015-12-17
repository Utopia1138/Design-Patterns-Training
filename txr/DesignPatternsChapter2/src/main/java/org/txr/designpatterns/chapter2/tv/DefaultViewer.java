package org.txr.designpatterns.chapter2.tv;

public class DefaultViewer implements Viewer{
	private int currentInterest;
	private ViewingBehaviour behaviour;
	private Display display;
	private String name;
	
	public DefaultViewer(String name, Display display) {
		this.name = name;
		this.display = display;
		display.addDisplayViewer(this);
	}

	public void programChanged(Program newProgram, Program oldProgram) {
		System.out.println( name + ": Switching to " +newProgram.getTitle());
		int value = behaviour.evaluateProgram(newProgram);
		currentInterest =+ value;
		if (checkBelowThreshold()) {
			System.out.println( name + ": I am really bored , going to" +behaviour.getAlternativeOccupation());
			display.removeDisplayViewer(this);
			return;
		}
		System.out.println( name + ": watching " +newProgram.getTitle());
	}
	
	private boolean checkBelowThreshold() {
		return currentInterest < behaviour.getSwitchingOffThreshHold();
	}

	public void setViewingBehaviour(ViewingBehaviour behaviour) {
		this.behaviour = behaviour;
		
	}

}
