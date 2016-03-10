package org.txr.designpatterns.chapter6;
class Network (reporters : List[Reporter]) {
  
  def broadcastElectionsNews(): Unit =  {
    reporters.foreach(_.report()) 
   }
}
