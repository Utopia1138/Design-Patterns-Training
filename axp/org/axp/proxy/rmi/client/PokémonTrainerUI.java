package org.axp.proxy.rmi.client;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.text.DefaultCaret;

public class PokémonTrainerUI extends JFrame {
	private static final long serialVersionUID = -7330744381281649192L;
	private static final int DURATION = 45;

	private PokémonTrainer trainer;
	private JTextArea outField;
	
	public PokémonTrainerUI( String name ) {
		super( name + ", Pokémon trainer extraordinaire" );
		setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		outField = new JTextArea( 30, 50 );
		outField.setEditable( false );
		((DefaultCaret) outField.getCaret()).setUpdatePolicy( DefaultCaret.ALWAYS_UPDATE );
		
		add( new JScrollPane( outField, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER ) );
		pack();
		
		trainer = new PokémonTrainer( name, new TextAreaAppender( outField ) );
	}
	
	public void begin( int duration ) {
		trainer.doJourney( duration );
		outField.append( "DONE" );
	}
	
	public static void main( String...args ) {
		String name = JOptionPane.showInputDialog( "First, what is your name?" );
		
		if ( name == null ) {
			return;
		}
		
		PokémonTrainerUI ui = new PokémonTrainerUI( name );
		ui.setVisible( true );
		ui.begin( DURATION );
	}
}
