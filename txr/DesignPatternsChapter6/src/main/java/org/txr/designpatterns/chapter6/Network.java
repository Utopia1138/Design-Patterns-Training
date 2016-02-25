package org.txr.designpatterns.chapter6;

import java.util.List;

/**
 * the invoker
 * @author thierry
 *
 */
public class Network {
	private List<Reporter> reporters;
	
	public Network(List<Reporter> reporters) {
		super();
		this.reporters = reporters;
	}

	public void broadcastElectionsNews() {
		for (Reporter reporter : reporters) {
			reporter.report();
		}
	}
}
