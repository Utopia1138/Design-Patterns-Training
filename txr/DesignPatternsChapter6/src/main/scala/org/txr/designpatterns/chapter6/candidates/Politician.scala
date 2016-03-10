package org.txr.designpatterns.chapter6.candidates

trait Politician {
  def quote(message:String): Unit = {
    System.out.println(this+":\n " + message + "\n")
  }
  override def toString(): String = {
    this.getClass().getSimpleName()
  }
}
