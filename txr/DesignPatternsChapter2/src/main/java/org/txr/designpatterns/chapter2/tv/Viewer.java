package org.txr.designpatterns.chapter2.tv;

public interface Viewer {
	public void programChanged (Program newProgram, Program oldProgram);
	public void setViewingBehaviour(ViewingBehaviour behaviour);
}
