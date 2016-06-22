package org.txr.designpatterns.chapter6;

import java.util.ArrayList;
import java.util.List;

import org.txr.designpatterns.chapter6.candidates.BernieSanders;
import org.txr.designpatterns.chapter6.candidates.DonaldTrump;
import org.txr.designpatterns.chapter6.candidates.HillaryClinton;
import org.txr.designpatterns.chapter6.candidates.JebBush;
import org.txr.designpatterns.chapter6.candidates.MarcoRubio;
import org.txr.designpatterns.chapter6.candidates.TedCruz;
import org.txr.designpatterns.chapter6.reporters.DonaldTrumpEmbedded;
import org.txr.designpatterns.chapter6.reporters.HillaryFollower;
import org.txr.designpatterns.chapter6.reporters.JebBushConsigned;
import org.txr.designpatterns.chapter6.reporters.MarcoRubioAfficionado;
import org.txr.designpatterns.chapter6.reporters.SandersReporter;
import org.txr.designpatterns.chapter6.reporters.TedCruzDisciple;
/*
 * the client
 */
public class Campaign {

	public static void main (String [] args) {
		
	
		
		Network network = new Network(buildReporterList());
		network.broadcastElectionsNews();
		
	}
	
	public static List<Reporter> buildReporterList() {
		List<Reporter> list = new ArrayList<Reporter>();
		list.add(new DonaldTrumpEmbedded(new DonaldTrump()));
		list.add(new HillaryFollower(new HillaryClinton()));
		list.add(new SandersReporter(new BernieSanders()));
		list.add(new MarcoRubioAfficionado(new MarcoRubio()));
		list.add(new TedCruzDisciple(new TedCruz()));
		list.add(new JebBushConsigned(new JebBush()));
		return list;
	}
	 
}
