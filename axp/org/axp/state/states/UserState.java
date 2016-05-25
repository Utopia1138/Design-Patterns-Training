package org.axp.state.states;

import org.axp.state.GameEventController;
import org.axp.state.XMLState;

/**
 * In this state we read in the user's name. We could extend this with some more data, in which case we'd
 * want an actual <tt>User</tt> entity, but for now we just pass a plain {@link String} to the controller
 * when we're done parsing. We then return to the {@link ReadyState}.
 */
public class UserState extends XMLState {
	private String userId;
	private String userName;
	
	public UserState( GameEventController controller, String userId ) {
		super( controller );
		this.userId = userId;
	}
	
	@Override
	public void characters( char[] ch, int start, int length ) {
		String value = new String( ch, start, length ).trim();
		
		if ( !"".equals( value ) ) {
			this.userName = value;
		}
	}
	
	@Override
	public void endElement( String uri, String localName, String qName ) {
		if ( "user".equals( qName ) ) {
			controller.addUser( userId, userName );
			controller.setState( new ReadyState( controller ) );
		}
		else {
			super.endElement( uri, localName, qName );
		}
	}
}
