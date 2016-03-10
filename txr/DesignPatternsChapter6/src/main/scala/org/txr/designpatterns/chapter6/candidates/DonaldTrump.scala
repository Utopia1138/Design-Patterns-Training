package org.txr.designpatterns.chapter6.candidates
class DonaldTrump extends Politician {
  def offensiveQuote(): Unit = {
    quote( " You could see there was blood coming out of her eyes."
              + " Blood coming out of her wherever")
  }
  def talkAboutMexico(): Unit = {
    quote(" I will build a great wall – and nobody builds walls "
              + "better than me, believe me —and I'll build them very inexpensively.\n "
                      + " I will build a great, great wall on our southern border, "
                              + "and I will make Mexico pay for that wall. "
                                      + "Mark my words")
  }
  def classy(): Unit = {
    quote(" If Hillary Clinton can't satisfy her husband what makes her think she can satisfy America")
  }
}
