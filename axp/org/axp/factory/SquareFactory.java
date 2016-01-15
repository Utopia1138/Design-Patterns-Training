package org.axp.factory;

import java.awt.Point;

import org.axp.factory.shape.Shape;
import org.axp.factory.shape.Square;

/**
 * Create a square of random colour and varying edge length
 */
public class SquareFactory extends ShapeFactory {
	private final int squareEdge;
	
	public SquareFactory( int squareEdge ) {
		this.squareEdge = squareEdge;
	}
	
	@Override
	public Shape create( Point centre ) {
		return new Square( centre, randColour(), varySize( this.squareEdge ) );
	}
}
