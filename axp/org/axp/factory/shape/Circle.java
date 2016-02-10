package org.axp.factory.shape;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

/**
 * A circle of specified radius and colour.
 */
public class Circle extends Shape {
	protected int radius;
	
	public Circle( Point centre, Color colour, int radius ) {
		super( centre, colour );
		this.radius = radius;
	}

	@Override
	public void draw( Graphics g ) {
		g.fillOval( this.centre.x - this.radius, this.centre.y - this.radius, this.radius * 2, this.radius * 2 );
	}
}
