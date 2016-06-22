package org.txr.designpatterns.chapter7
object Adapters {
  implicit class CassetteToMusicPlayerAdapter (cassette : CassettePlayer) extends MusicPlayer {
    def playMusic(song:String) { cassette.pressPlay(song)}
  }
}
