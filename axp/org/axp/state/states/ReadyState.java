package org.axp.state.states;

import org.axp.state.GameEventController;
import org.axp.state.XMLState;
import org.axp.state.entity.Game;
import org.xml.sax.Attributes;

/**
 * This is the main state of the XML parser. We wait for users to log in (&lt;user&gt;) and out
 * (&lt;logoff/&gt;) and for games to be created (&lt;game&gt;) and finish (&lt;finishGame/&gt;),
 * moving into the {@link UserState} and {@link GameState} respectively.
 * 
 * Once we finally see the end &lt;/channel&gt; element, we disconnect and move to the {@link FinalState}.
 */
public class ReadyState extends XMLState {
	public ReadyState( GameEventController controller ) {
		super( controller );
	}
	
	@Override
	public void startElement( String uri, String localName, String qName, Attributes attributes ) {
		if ( "user".equals( qName ) ) {
			String userId = getId( "User", attributes );
			controller.setState( new UserState( controller, userId ) );
		}
		else if ( "logoff".equals( qName ) ) {
			String userId = getId( "Log off", attributes );
			controller.removeUser( userId );
		}
		else if ( "game".equals( qName ) ) {
			String gameId = getId( "Game", attributes );
			Game game = new Game( getAttr( "Game", "type", attributes ) );
			controller.addGame( gameId, game );
			controller.setState( new GameState( controller, game ) );
		}
		else if ( "finishGame".equals( qName ) ) {
			String gameId = getId( "Finish game", attributes );
			String winner = attributes.getValue( "winner" );
			controller.finishGame( gameId, winner );
		}
		else {
			super.startElement( uri, localName, qName, attributes );
		}
	}

	@Override
	public void endElement( String uri, String localName, String qName ) {
		if ( "channel".equals( qName ) ) {
			controller.disconnect();
			controller.setState( new FinalState( controller ) );
		}
		else if ( "logoff".equals( qName ) || "finishGame".equals( qName ) ) {
			/* Ignore end of logoff/finishGame elements */
		}
		else {
			super.endElement( uri, localName, qName );
		}
	}
}
