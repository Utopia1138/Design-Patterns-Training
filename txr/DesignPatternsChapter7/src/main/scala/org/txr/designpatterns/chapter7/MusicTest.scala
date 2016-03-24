package org.txr.designpatterns.chapter7
import org.txr.designpatterns.chapter7.Adapters._
// object are kind of like singleton
object MusicTest extends App {
 // a music player 
  val player:MusicPlayer = MP3Player() 
  player.playMusic("Riders on the storm")
 // a antiquated cassette playyer 
  val cassette:CassettePlayer = new CassettePlayer()
  cassette.pressPlay("Riders on the storm")
  
// a casset player has become a music player by magic
  val otherPlayer:MusicPlayer = new CassettePlayer()
  otherPlayer.playMusic("Riders on the storm")

}
