package org.txr.designpatterns.chapter6.reporters;

import org.txr.designpatterns.chapter6.Reporter;
import org.txr.designpatterns.chapter6.candidates.BernieSanders;

public class SandersReporter implements Reporter{
	private BernieSanders sanders;

	public SandersReporter(BernieSanders sanders) {
		super();
		this.sanders = sanders;
	}

	public void report() {
		sanders.leftRant();
		sanders.tooBig();
		
	}
	
	
}
