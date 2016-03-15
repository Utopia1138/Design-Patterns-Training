package org.txr.designpatterns.chapter6.candidates;
/*
 * receiver
 */
public class DonaldTrump extends Politician{

	public void offensiveQuote() {
		quote( " You could see there was blood coming out of her eyes."
				+ " Blood coming out of her wherever");
	}
	
	public void talkAboutMexico() {
		quote(" I will build a great wall – and nobody builds walls "
				+ "better than me, believe me —and I'll build them very inexpensively.\n "
				+ " I will build a great, great wall on our southern border, "
				+ "and I will make Mexico pay for that wall. "
				+ "Mark my words");
	}
	
	public void classy() {
		quote(" If Hillary Clinton can't satisfy her husband what makes her think she can satisfy America");
	}
}
