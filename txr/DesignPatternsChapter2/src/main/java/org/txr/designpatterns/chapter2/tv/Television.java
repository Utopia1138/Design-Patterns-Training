package org.txr.designpatterns.chapter2.tv;

import java.util.HashSet;
import java.util.Set;

public class Television implements Display{
	private Set<Viewer> viewers = new HashSet<Viewer>();
	private Program currentProgram;
	
	
	public void addDisplayViewer(Viewer viewer) {
		viewers.add(viewer);
		
	}

	public void removeDisplayViewer(Viewer viewer) {
		viewers.remove(viewer);
	}
	
	public void programChanged(Program newProgram, Program oldProgram) {
		Set<Viewer> currentViewers = new HashSet<Viewer>(viewers);
		for (Viewer viewer: currentViewers) {
			viewer.programChanged(newProgram, oldProgram);
		}
	}

	public Program getCurrentProgram() {
		return currentProgram;
	}

	public void setProgram(Program program) {
		if (!program.equals(this.currentProgram)) {
			Program oldProgram = this.currentProgram;
			this.currentProgram = program;
			programChanged(currentProgram,oldProgram);
		}
	}

}
