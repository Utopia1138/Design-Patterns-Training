package org.txr.designpatterns.chapter6
import org.txr.designpatterns.chapter6.candidates._

trait Reporter {
  def report(): Unit
}
case class RubioAfficionado(rubio: MarcoRubio) extends Reporter {
  override def report(): Unit = {
      rubio.selfMadeMan()
      rubio.theManMachine()
 }
}
case class DonalTrumpEmbedded(donald: DonaldTrump) extends Reporter {
  override def report(): Unit = {
      donald.offensiveQuote()
      donald.talkAboutMexico()
      donald.classy()
  }
}
