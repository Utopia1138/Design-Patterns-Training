package org.axp.state.states;

import org.axp.state.GameEventController;
import org.axp.state.XMLState;
import org.xml.sax.Attributes;

/**
 * Initial state of the XML parser. We wait around for the first &lt;channel&gt; start tag, and then
 * move into the {@link ReadyState}.
 */
public class InitialState extends XMLState {
	public InitialState( GameEventController controller ) {
		super( controller );
	}
	
	@Override
	public void startDocument() {
		/* We allow this, but don't change state until we actually see the root element */
	}
	
	@Override
	public void startElement( String uri, String localName, String qName, Attributes attributes ) {
		if ( "channel".equals( qName ) ) {
			controller.connect();
			controller.setState( new ReadyState( controller ) );
		}
		else {
			super.startElement( uri, localName, qName, attributes );
		}
	}
}
