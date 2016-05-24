package org.axp.state;

import org.axp.state.ui.ClientView;
import org.xml.sax.InputSource;

public class RunGame {

	public static void main( String...args ) {
		GameEventController controller = new GameEventController();
		ClientView ui = new ClientView( controller );
		controller.addObserver( ui );
		ui.setVisible( true );
		
		try {
			controller.parse( new InputSource( System.in ) );
		}
		catch ( Exception e ) {
			e.printStackTrace();
		}
	}
}
