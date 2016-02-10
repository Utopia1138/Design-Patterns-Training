package org.axp.factory.shape;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.geom.AffineTransform;

/**
 * A "snowflake" is actually a six-pointed cross. It can have a custom rotation, colour, and radius (arm length).
 */
public class Snowflake extends Shape {
	protected int radius;
	protected double rotation;
	
	public Snowflake( Point centre, Color colour, double rotation, int radius ) {
		super( centre, colour );
		this.radius = radius;
		this.rotation = rotation;
	}
	
	protected Point rotateAboutCentre( Point target, double radians ) {
		Point result = new Point();
		AffineTransform rotation = new AffineTransform(); // 'tis affine transform, cap'n
		rotation.rotate( radians, this.centre.x, this.centre.y );
		rotation.transform( target, result );
		return result;
	}

	@Override
	public void draw( Graphics g ) {
		Point right = new Point( this.centre.x + this.radius, this.centre.y );
		Point left = new Point( this.centre.x - this.radius, this.centre.y );
		
		/* Ideally we'd store these points rather than calculating them every time, but meh */
		
		Point a1 = rotateAboutCentre( right, this.rotation );
		Point a2 = rotateAboutCentre( left, this.rotation );
		g.drawLine( a1.x, a1.y, a2.x, a2.y );
		
		Point b1 = rotateAboutCentre( a1, Math.PI / 3 );
		Point b2 = rotateAboutCentre( a2, Math.PI / 3 );
		g.drawLine( b1.x, b1.y, b2.x, b2.y );
		
		Point c1 = rotateAboutCentre( a1, Math.PI * 2/3 );
		Point c2 = rotateAboutCentre( a2, Math.PI * 2/3 );
		g.drawLine( c1.x, c1.y, c2.x, c2.y );
	}
}
