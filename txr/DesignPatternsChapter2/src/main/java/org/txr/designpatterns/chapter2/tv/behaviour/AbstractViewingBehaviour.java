package org.txr.designpatterns.chapter2.tv.behaviour;

import org.txr.designpatterns.chapter2.tv.ViewingBehaviour;

public abstract class AbstractViewingBehaviour implements ViewingBehaviour {
	protected int thresholdValue;
	
	public void setSwitchingOffThreshHold(int value) {
		this.thresholdValue = value;
	}
	public int getSwitchingOffThreshHold() {
		
		return thresholdValue;
	}


}
