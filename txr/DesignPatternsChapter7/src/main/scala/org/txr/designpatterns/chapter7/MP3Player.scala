package org.txr.designpatterns.chapter7
case class MP3Player() extends MusicPlayer {
  override def playMusic (song:String) {
    println("MP3 playing " + song);
  }
}

