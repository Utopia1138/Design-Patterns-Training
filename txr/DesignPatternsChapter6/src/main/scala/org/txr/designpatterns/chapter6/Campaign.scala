package org.txr.designpatterns.chapter6
import  org.txr.designpatterns.chapter6.candidates._

object Campaign extends App {
  Network n = Newtwork(reporters())
  n.broadcastElectionsNews()

  def reporters(): List[Reporter] = {
    return List[Reporter](RubioAfficionado(MarcoRubio()),DonalTrumpEmbedded(DonalTrump()))
  }
}
