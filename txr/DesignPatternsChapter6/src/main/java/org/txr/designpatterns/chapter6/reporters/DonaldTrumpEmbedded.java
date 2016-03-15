package org.txr.designpatterns.chapter6.reporters;

import org.txr.designpatterns.chapter6.Reporter;
import org.txr.designpatterns.chapter6.candidates.DonaldTrump;

public class DonaldTrumpEmbedded implements Reporter {
	
	private DonaldTrump trump;
	
	public DonaldTrumpEmbedded(DonaldTrump trump) {
		super();
		this.trump = trump;
	}

	public void report() {
		trump.offensiveQuote();
		trump.talkAboutMexico();
		trump.classy();
		
	}

}
