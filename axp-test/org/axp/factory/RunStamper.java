package org.axp.factory;

import org.axp.factory.controller.Stamper;
import org.axp.factory.ui.StamperFrame;

/**
 * Create a stamper controller, its factories, and UI.
 */
public class RunStamper {
	public static void main( String[] args ) {
		Stamper stamper = new Stamper();
		
		/*
		 * Note the loose coupling between the factories and the app itself. The user could
		 * happily write some of their own factory and shape classes here and load them in,
		 * or load them from a JAR file. Or this could be driven via a properties file and
		 * reflection, thus removing the need to update any code at all to get new shapes.
		 */
		stamper.addShape( "circle", new CircleFactory( 15 ) );
		stamper.addShape( "square", new SquareFactory( 26 ) );
		stamper.addShape( "snowflake", new SnowflakeFactory( 18 ) );
		
		new StamperFrame( stamper, 600, 400 ).setVisible( true );
	}
}
