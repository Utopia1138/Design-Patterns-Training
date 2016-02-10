package org.axp.factory.ui;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JPanel;

import org.axp.factory.controller.Stamper;
import org.axp.factory.shape.Shape;

/**
 * Just a boring UI class here; no business logic to speak of. (As it should be).
 */
public class StamperPanel extends JPanel {
	private static final long serialVersionUID = -2474788166040749485L;
	
	private final Stamper controller;
	
	public StamperPanel( Stamper controller ) {
		super();
		this.controller = controller;
		addMouseListener( controller );
	}
	
	@Override
	public void paintComponent( Graphics g ) {
		if ( g instanceof Graphics2D ) {
			Graphics2D g2d = (Graphics2D) g;
			g2d.setRenderingHint( RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON );
		}
		
		super.paintComponent( g );
		
		for ( Shape s : this.controller.getShapes() ) {
			s.setColour( g );
			s.draw( g );
		}
	}
}
