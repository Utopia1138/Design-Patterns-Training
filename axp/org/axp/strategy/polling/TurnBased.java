package org.axp.strategy.polling;

import java.util.List;

public class TurnBased implements PollingBehaviour {
	private int index = 0;
	
	@Override
	public String getNextPlayer( List<String> players ) {
		if ( players.isEmpty() ) throw new IllegalStateException( "No players!" );
		
		String player = players.get( this.index++ );
		
		if ( this.index >= players.size() ) {
			this.index = 0;
		}
		
		return player;
	}

}
