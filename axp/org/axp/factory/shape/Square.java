package org.axp.factory.shape;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

/**
 * A square with a custome edge length and colour.
 */
public class Square extends Shape {
	protected int he; // Half-edge
	
	public Square( Point centre, Color colour, int edge ) {
		super( centre, colour );
		this.he = edge / 2;
	}

	@Override
	public void draw( Graphics g ) {
		g.fillRect( this.centre.x - this.he, this.centre.y - this.he, this.he * 2, this.he * 2 );
	}
}
