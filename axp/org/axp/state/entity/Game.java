package org.axp.state.entity;

import java.util.ArrayList;
import java.util.TreeMap;

/**
 * An entity representing the state of some game on the server. We see who's playing, some rule variations,
 * and if it is finished. (And if so, who won).
 */
public class Game {
	private String gameType;
	private ArrayList<String> players = new ArrayList<>();
	private TreeMap<String,String> rules = new TreeMap<>();
	private boolean finished;
	private String winner;
	
	public Game( String gameType ) {
		this.gameType = gameType;
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder( this.gameType );
		
		for ( String p : this.players ) {
			sb.append( "\n\t" ).append( p );
			
			if ( p.equals( this.winner ) ) {
				sb.append( " *" );
			}
		}
		
		for ( String rule : this.rules.keySet() ) {
			sb.append( "\n\t" ).append( rule ).append( " = " ).append( this.rules.get( rule ) );
		}
		
		return sb.toString();
	}

	public void addPlayer( String player ) {
		this.players.add( player );
	}
	
	public void addRule( String ruleName, String value ) {
		this.rules.put( ruleName, value);
	}

	public String getGameType() {
		return this.gameType;
	}
	
	public String[] getPlayers() {
		return this.players.toArray( new String[] {} );
	}
	
	public String[] getRules() {
		String[] ruleList = new String[ this.rules.size() ];
		int idx = 0;
		
		for ( String r : this.rules.keySet() ) {
			ruleList[idx++] = r + " = " + this.rules.get( r );
		}
		
		return ruleList;
	}
	
	public boolean isFinished() {
		return this.finished;
	}
	
	public String getWinner() {
		return this.winner;
	}
	
	public void setFinished( String winner ) {
		this.finished = true;
		this.winner = winner;
	}
}
