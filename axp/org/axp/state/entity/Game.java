package org.axp.state.entity;

import java.util.ArrayList;
import java.util.TreeMap;

public class Game {
	private String gameType;
	private ArrayList<String> players = new ArrayList<>();
	private TreeMap<String,String> rules = new TreeMap<>();
	
	public Game( String gameType ) {
		this.gameType = gameType;
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder( this.gameType );
		
		for ( String p : this.players ) {
			sb.append( "\n\t" ).append( p );
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
}
