package org.axp.builder.builder;

import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.util.function.Supplier;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class EntityFrame<E> extends JFrame implements ComponentListener {
	private static final long serialVersionUID = 6934127159559802729L;

	public EntityFrame( EntityPanel<E> panel, Supplier<E> generator ) {
		super( "Employees" );
		setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		
		JPanel root = new JPanel();
		root.setBorder( new EmptyBorder( 5, 8, 8, 8 ) );
		
		root.add( panel );
		
		panel.addComponentListener( this );
		
		add( root );
		pack();
	}

	@Override
	public void componentResized( ComponentEvent e ) {
		pack();
	}

	@Override public void componentMoved(ComponentEvent e) {}
	@Override public void componentShown(ComponentEvent e) {}
	@Override public void componentHidden(ComponentEvent e) {}
}
