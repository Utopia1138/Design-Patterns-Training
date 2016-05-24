package org.axp.state.states;

import org.axp.state.GameEventController;
import org.axp.state.XMLState;
import org.axp.state.entity.Game;
import org.xml.sax.Attributes;

public class GameState extends XMLState {
	private Game game;
	
	public GameState( GameEventController controller, Game game ) {
		super( controller );
		this.game = game;
	}
	@Override
	public void startElement( String uri, String localName, String qName, Attributes attributes ) {
		if ( "player".equals( qName ) ) {
			String userId = getId( "Player", attributes );
			this.game.addPlayer( controller.getUser( userId ) );
			this.controller.updateGames();
		}
		else if ( "rule".equals( qName ) ) {
			String ruleName = getAttr( "Rule", "name", attributes );
			String setting = getAttr( "Rule", "setting", attributes );
			this.game.addRule( ruleName, setting );
			this.controller.updateGames();
		}
		else {
			super.startElement( uri, localName, qName, attributes );
		}
	}
	
	@Override
	public void endElement( String uri, String localName, String qName ) {
		if ( "game".equals( qName ) ) {
			controller.setState( new ReadyState( controller ) );
		}
		else if ( "player".equals( qName ) || "rule".equals( qName ) ) {
			/* Ignore end of player/rule elements */
		}
		else {
			super.endElement( uri, localName, qName );
		}
	}
}
