package org.axp.mvc.view;

import javax.swing.JLabel;

public class IntLabel extends JLabel {
	private static final long serialVersionUID = -2094681898519720510L;
	private int value = 0;

	public IntLabel() {
		this( 0 );
	}
	
	public IntLabel( int initial ) {
		super( Integer.toString( initial ) );
		value = initial;
	}
	
	private void update() {
		super.setText( Integer.toString( value ) );
	}
	
	public void setNumber( int number ) {
		value = number;
		update();
	}
	
	public void add( int number ) {
		value += number;
		update();
	}
	
	public void subtract( int number ) {
		value -= number;
		update();
	}
	
	public void increment() {
		value++;
		update();
	}
	
	public void decrement() {
		value--;
		update();
	}
	
	@Override
	public void setText( String text ) {
		setNumber( Integer.parseInt( text ) );
	}
}
