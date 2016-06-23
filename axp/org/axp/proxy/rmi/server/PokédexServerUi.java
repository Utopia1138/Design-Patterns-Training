package org.axp.proxy.rmi.server;

import java.awt.GridLayout;
import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.BevelBorder;

import org.axp.proxy.rmi.Pokémon;

public class PokédexServerUi extends JFrame implements Observer {
	private static final long serialVersionUID = 5055897627107032689L;
	private static final int COLUMNS = 5;
	private static final int ROWS = 2;
	private static final int GAP = 5;
	private static final int FRAME_WIDTH = 660;
	private static final int FRAME_HEIGHT = 600;

	private HashMap<Pokémon,JTextArea> labels = new HashMap<>( COLUMNS * ROWS );
	
	public PokédexServerUi() {
		super( "Professor Oak's Lab" );
		setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		getContentPane().setLayout( new GridLayout( COLUMNS, ROWS, GAP, GAP ) );
		setSize( FRAME_WIDTH, FRAME_HEIGHT );
	}

	@Override
	public void update( Observable o, Object arg ) {
		if ( arg == null ) {
			System.err.println( "Nothing passed!" );
		}
		
		if ( !( arg instanceof Pokémon ) ) {
			System.err.println( "Dunno how to handle " + arg.getClass().getName() );
		}
		
		Pokémon monster = (Pokémon) arg;
		
		if ( labels.containsKey( monster ) ) {
			labels.get( monster ).setText( monster.toString() );
		}
		else {
			JTextArea newLabel = new JTextArea( monster.toString() );
			newLabel.setEditable( false );
			newLabel.setBorder( BorderFactory.createBevelBorder( BevelBorder.LOWERED ) );
			add( new JScrollPane( newLabel, JScrollPane.VERTICAL_SCROLLBAR_NEVER, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED ) );
			revalidate();
			labels.put( monster, newLabel );
		}
	}
	
	public static void main( String...args ) {
		try {
			PokédexServerUi ui = new PokédexServerUi();
			ui.setVisible( true );
			
			PokédexServer server = new PokédexServer();
			server.addObserver( ui );
			server.run();
		}
		catch ( Exception e ) {
			e.printStackTrace();
			System.exit( 200 );
		}
	}
}
