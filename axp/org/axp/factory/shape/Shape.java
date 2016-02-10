package org.axp.factory.shape;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

/**
 * Some sort of shape. Has a colour, too.
 */
public abstract class Shape {
	protected Point centre;
	protected Color colour;
	
	/** Shape with a centre point and a colour */
	public Shape( Point centre, Color colour ) {
		this.centre = centre;
		this.colour = colour;
	}
	
	/** Default to black */
	public Shape( Point centre ) {
		this( centre, Color.BLACK );
	}
	
	/** Set the colour */
	public void setColour( Graphics g ) {
		g.setColor( this.colour );
	}
	
	/** Override this method to actually draw the shape */
	public abstract void draw( Graphics g );
}
