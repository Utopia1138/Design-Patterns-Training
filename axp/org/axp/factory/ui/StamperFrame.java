package org.axp.factory.ui;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.axp.factory.controller.Stamper;

/**
 * Just a boring UI class here; no business logic to speak of. (As it should be).
 */
public class StamperFrame extends JFrame implements ChangeListener {
	private static final long serialVersionUID = 7602970085192178257L;
	
	private final StamperPanel panel;

	public StamperFrame( Stamper controller, int width, int height ) {
		super( "Stamper" );
		this.panel = new StamperPanel( controller );
		controller.setChangeListener( this );
		
		setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		add( this.panel );
		setSize( width, height );
		
		setJMenuBar( createMenuBar( controller ) );
	}
	
	private JMenuBar createMenuBar( Stamper controller ) {
		JMenuBar bar = new JMenuBar();
		JMenu shapeMenu = new JMenu( "Shapes" );
		ButtonGroup shapeGroup = new ButtonGroup();
		
		for ( String shape : controller.getShapeNames() ) {
			JRadioButtonMenuItem shapeOption = new JRadioButtonMenuItem( shape );
			shapeMenu.add( shapeOption );
			shapeGroup.add( shapeOption );
			shapeOption.setActionCommand( shape );
			shapeOption.addActionListener( controller );
		}
		
		shapeMenu.getItem( 0 ).setSelected( true );
		
		bar.add( shapeMenu );
		return bar;
	}

	@Override
	public void stateChanged( ChangeEvent evt ) {
		this.panel.repaint();
	}
}
