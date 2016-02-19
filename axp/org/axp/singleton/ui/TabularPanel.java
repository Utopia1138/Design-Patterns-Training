package org.axp.singleton.ui;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JPanel;

/**
 * Just a UI convenience class, because GridBagLayout is awful.
 */
public class TabularPanel extends JPanel {
	private static final long serialVersionUID = -7255996829941293215L;
	
	private GridBagConstraints constraints  = new GridBagConstraints();
	private int currRow = 0;
	
	public TabularPanel( int padY, int padX ) {
		super();
		setLayout( new GridBagLayout() );
		
		this.constraints.fill = GridBagConstraints.BOTH;
		this.constraints.ipady = padY;
		this.constraints.ipadx = padX;
	}
	
	public void set( int y, int x, Component component ) {
		set( y, x, 1, component );
	}
	
	public void set( int y, int x, int width, Component component ) {
		this.constraints.gridy = y;
		this.constraints.gridx = x;
		this.constraints.gridwidth = width;
		super.add( component, this.constraints );
	}
	
	public void setRow( int y, Component...components ) {
		for ( int i = 0; i < components.length; i++ ) {
			set( y, i, components[i] );
		}
	}
	
	public void setRow( int y, int width, Component component ) {
		set( y, 0, width, component );
	}
	
	public void addRow( Component...components ) {
		setRow( this.currRow++, components );
	}
	
	public void addRow( int width, Component component ) {
		setRow( this.currRow++, width, component );
	}
}
