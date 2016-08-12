package org.axp.builder.builder;

import java.awt.Component;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * No builder logic here. Just a convenient JFrame class which will automatically repack
 * its layout when its single child resizes.
 * 
 * @author Alex P
 */
public class ResizingFrame extends JFrame implements ComponentListener {
	private static final long serialVersionUID = 6934127159559802729L;

	public ResizingFrame( Component singleComponent, String title ) {
		super( title );
		setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );

		singleComponent.addComponentListener( this );
		
		JPanel root = new JPanel();
		root.add( singleComponent );
		add( root );
		pack();
	}

	@Override
	public void componentResized( ComponentEvent e ) {
		pack();
	}

	@Override public void componentMoved( ComponentEvent e ) {}
	@Override public void componentShown( ComponentEvent e ) {}
	@Override public void componentHidden( ComponentEvent e ) {}
}
