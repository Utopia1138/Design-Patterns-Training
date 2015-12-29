package org.txr.designpatterns.chapter2.tv;

public interface ViewingBehaviour {
	public int evaluateProgram (Program program);
	public void setSwitchingOffThreshHold(int value);
	public int getSwitchingOffThreshHold();
	public String getAlternativeOccupation();
}
