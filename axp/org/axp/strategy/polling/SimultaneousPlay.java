package org.axp.strategy.polling;

import java.util.List;
import java.util.Random;

public class SimultaneousPlay implements PollingBehaviour {
	private Random rand = new Random();

	@Override
	public String getNextPlayer( List<String> players ) {
		// TODO Once we replace String with a proper Player object, poll each in turn until they are ready.
		// For now, we just randomly choose one.
		return players.get( rand.nextInt( players.size() ) );
	}

}
