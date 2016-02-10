package org.axp.factory.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.TreeMap;

import javax.swing.event.ChangeListener;

import org.axp.factory.ShapeFactory;
import org.axp.factory.shape.Shape;

/**
 * Business logic class. Maintain a list of stamps, and respond to clicks and menu commands to
 * add stamps or change the stamp shape appropriately.
 * 
 * All the logic to actually create the Shape classes, however, is in the factories. 
 */
public class Stamper implements ActionListener, MouseListener {
	private ArrayList<Shape> stamps = new ArrayList<Shape>();
	private String currentShape;
	
	private final ArrayList<String> shapeNames = new ArrayList<String>();
	private final TreeMap<String, ShapeFactory> shapeFactories = new TreeMap<String, ShapeFactory>();
	
	private ChangeListener listener;
	
	public void addShape( String shapeName, ShapeFactory factory ) {
		this.shapeNames.add( shapeName );
		this.shapeFactories.put( shapeName, factory );
		
		if ( this.currentShape == null ) {
			this.currentShape = shapeName;
		}
	}
	
	public void setChangeListener( ChangeListener listener ) {
		this.listener = listener;
	}
	
	public Iterable<String> getShapeNames() {
		return this.shapeNames;
	}
	
	public Iterable<Shape> getShapes() {
		return this.stamps;
	}
	
	@Override
	public void actionPerformed( ActionEvent evt ) {
		this.currentShape = evt.getActionCommand();
	}
	
	@Override
	public void mouseClicked( MouseEvent squeak ) {
		ShapeFactory factory = this.shapeFactories.get( this.currentShape );
		this.stamps.add( factory.create( squeak.getPoint() ) );
		
		if ( this.listener != null ) {
			this.listener.stateChanged( null );
		}
	}

	@Override public void mouseEntered( MouseEvent squeak ) { /* nah */ }
	@Override public void mouseExited( MouseEvent squeak ) { /* nah */ }
	@Override public void mousePressed( MouseEvent squeak ) { /* nah */ }
	@Override public void mouseReleased( MouseEvent squeak ) { /* nah */ }
}
