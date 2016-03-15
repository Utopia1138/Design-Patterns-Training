package org.txr.designpatterns.chapter6.reporters;

import org.txr.designpatterns.chapter6.Reporter;
import org.txr.designpatterns.chapter6.candidates.HillaryClinton;

public class HillaryFollower implements Reporter{
	private HillaryClinton clinton;

	public HillaryFollower(HillaryClinton clinton) {
		super();
		this.clinton = clinton;
	}

	public void report() {
		clinton.hairStyle();
	}
	
}
