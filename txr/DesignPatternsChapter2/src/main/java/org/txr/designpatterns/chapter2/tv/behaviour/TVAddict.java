package org.txr.designpatterns.chapter2.tv.behaviour;

import org.txr.designpatterns.chapter2.tv.Program;

public class TVAddict extends AbstractViewingBehaviour {

	public int evaluateProgram(Program program) {
		// he/she loves everything
		return 5;
	}

	public String getAlternativeOccupation() {
		return " watch more TV";
	}

}
