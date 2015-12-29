package org.txr.designpatterns.chapter2.tv.behaviour;

import org.txr.designpatterns.chapter2.tv.Program;
import org.txr.designpatterns.chapter2.tv.ProgramType;
import org.txr.designpatterns.chapter2.tv.ViewingBehaviour;

public class HorrorFan extends AbstractViewingBehaviour {
	
	public int evaluateProgram(Program program) {
		if (program.getType() == ProgramType.HORROR_MOVIES) {
			return 5;
		} else if (program.getType() == ProgramType.NEWS) {
			return 1;
		} else if (program.getType() == ProgramType.SPORTS) {
			return -3;
		} else if ( program.getType() == ProgramType.DOCUMENTARY) {
			return -1;
		}
		// anything else return -1
		return -1;
	}

	

	public String getAlternativeOccupation() {
		return " read another Stephen King";
	}

	
}
