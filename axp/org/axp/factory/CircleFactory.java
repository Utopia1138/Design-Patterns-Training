package org.axp.factory;

import java.awt.Point;

import org.axp.factory.shape.Circle;
import org.axp.factory.shape.Shape;

/**
 * Create a circle of a random colour and varying radius
 */
public class CircleFactory extends ShapeFactory {
	private final int circleRadius;
	
	public CircleFactory( int circleRadius ) {
		this.circleRadius = circleRadius;
	}
	
	@Override
	public Shape create( Point centre ) {
		return new Circle( centre, randColour(), varySize( this.circleRadius ) );
	}
}
