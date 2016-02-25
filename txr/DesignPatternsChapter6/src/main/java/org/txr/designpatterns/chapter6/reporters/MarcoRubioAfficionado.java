package org.txr.designpatterns.chapter6.reporters;

import org.txr.designpatterns.chapter6.Reporter;
import org.txr.designpatterns.chapter6.candidates.MarcoRubio;

public class MarcoRubioAfficionado implements Reporter {
	private MarcoRubio rubio;
	
	public MarcoRubioAfficionado(MarcoRubio rubio) {
		super();
		this.rubio = rubio;
	}

	public void report() {
		rubio.selfmademan();
		rubio.theManMachine();
	}

}
