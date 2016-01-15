package org.axp.factory;

import java.awt.Point;

import org.axp.factory.shape.Shape;
import org.axp.factory.shape.Snowflake;

/**
 * Create a "snowflake" (actually just a six-pointed cross) of random colour, rotation, and varying arm length
 */
public class SnowflakeFactory extends ShapeFactory {
	private final int armLength;
	
	public SnowflakeFactory( int armLength ) {
		this.armLength = armLength;
	}
	
	@Override
	public Shape create( Point centre ) {
		return new Snowflake( centre, randColour( 0.8f ), randRotation(), varySize( this.armLength ) );
	}
}
