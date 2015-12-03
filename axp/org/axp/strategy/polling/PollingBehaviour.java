package org.axp.strategy.polling;

import java.util.List;

public interface PollingBehaviour {
	String getNextPlayer( List<String> players );
}
