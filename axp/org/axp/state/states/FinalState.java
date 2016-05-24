package org.axp.state.states;

import org.axp.state.GameEventController;
import org.axp.state.XMLState;

public class FinalState extends XMLState {
	public FinalState( GameEventController controller ) {
		super( controller );
	}
	
	@Override
	public void endDocument() {
		controller.finish();
	}
}
