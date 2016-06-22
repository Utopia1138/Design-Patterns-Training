package org.txr.designpatterns.chapter6.reporters;

import org.txr.designpatterns.chapter6.Reporter;
import org.txr.designpatterns.chapter6.candidates.TedCruz;

public class TedCruzDisciple implements Reporter{
	TedCruz cruz;
	public TedCruzDisciple(TedCruz tedCruz) {
		this.cruz = tedCruz;
	}
	public void report() {
		cruz.obamacare();
		cruz.twentyYears();
		
		
	}

}
