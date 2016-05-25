package org.axp.state.states;

import org.axp.state.GameEventController;
import org.axp.state.XMLState;

/**
 * Final state of the XML parser; we've seen the end &lt;/channel*gt; tag, and are just
 * awaiting the document end.
 */
public class FinalState extends XMLState {
	public FinalState( GameEventController controller ) {
		super( controller );
	}
	
	@Override
	public void endDocument() {
		controller.finish();
	}
}
