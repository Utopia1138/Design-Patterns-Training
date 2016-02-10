package org.axp.factory;

import java.awt.Color;
import java.awt.Point;
import java.util.Random;

import org.axp.factory.shape.Shape;

/**
 * The base factory class. This is a Method Factory pattern with the method in question being {@link #create(Point)}.
 * 
 * All other methods are utility methods that various subclasses can call on.
 */
public abstract class ShapeFactory {
	protected final Random rand = new Random();
	
	/** Random colour of the rainbow */
	protected Color randColour() {
		return randColour( 1f );
	}
	
	/** Random colour of the rainbow, custom darkness */
	protected Color randColour( float value ) {
		float hue = rand.nextFloat();
		return Color.getHSBColor( hue, 1f, value );
	}
	
	/** Random rotation in radians */
	protected double randRotation() {
		return this.rand.nextDouble() * 2 * Math.PI; // Two pie?
	}
	
	/** Randomly scale a base size to between 75% to 175% */
	protected int varySize( int baseSize ) {
		float multiplier = 0.75f + rand.nextFloat();
		return (int) ( baseSize * multiplier );
	}
	
	/**
	 * Abstract method that all concrete implementations must implement
	 * 
	 * @param centre the centre of the shape, i.e. where the user clicked
	 * @return a new {@link Shape} around that centre. Factors such as size and colour may vary.
	 */
	public abstract Shape create( Point centre );
}
