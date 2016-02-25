package org.txr.designpatterns.chapter6.reporters;

import org.txr.designpatterns.chapter6.Reporter;
import org.txr.designpatterns.chapter6.candidates.JebBush;

public class JebBushConsigned implements Reporter{
	private JebBush bush;

	public JebBushConsigned(JebBush bush) {
		super();
		this.bush = bush;
	}

	public void report() {
		bush.aLotOfThingsToSay();
		
	}
	
}
