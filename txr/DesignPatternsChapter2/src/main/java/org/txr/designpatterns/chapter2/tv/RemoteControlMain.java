package org.txr.designpatterns.chapter2.tv;

import org.txr.designpatterns.chapter2.tv.behaviour.HorrorFan;
import org.txr.designpatterns.chapter2.tv.behaviour.SportsFan;
import org.txr.designpatterns.chapter2.tv.behaviour.TVAddict;

public class RemoteControlMain {
	public static void main (String []args) {
		Television t = new Television();
	
		Program bbcNews = new Program("BBC News at 10", ProgramType.NEWS);
		Program scream = new Program("Scream", ProgramType.HORROR_MOVIES);
		Program bakeOff = new Program("Great british Bake off", ProgramType.ENTERTAINMENT);
		Program bluePlanet = new Program("Blue Planet", ProgramType.DOCUMENTARY);
		Program xFactor = new Program("XFactor",ProgramType.ENTERTAINMENT);
		Program footballGame = new Program("football",ProgramType.SPORTS);
		
		DefaultViewer hannibal  = new DefaultViewer("Hannibal", t);
		hannibal.setViewingBehaviour(new HorrorFan());
		DefaultViewer sporty  = new DefaultViewer("Sporty", t);
		sporty.setViewingBehaviour(new SportsFan());
		DefaultViewer tvAddict = new DefaultViewer("Norman", t);
		tvAddict.setViewingBehaviour(new TVAddict());
		
		t.setProgram(bbcNews);
		t.setProgram(footballGame);
		t.setProgram(bakeOff);
		t.setProgram(bluePlanet);
		t.setProgram(xFactor);
		t.setProgram(scream);
	}
}
