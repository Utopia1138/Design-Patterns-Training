package org.axp.mvc.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import org.axp.mvc.controller.ISweeper;
import org.axp.mvc.model.MineSquare;
import org.axp.mvc.model.Minefield;
import org.axp.mvc.rmi.RemoteObserver;

public class MinesweeperUI extends UnicastRemoteObject implements RemoteObserver<MineSquare>, MouseListener {
	private static final long serialVersionUID = -6043526207968115429L;
	private static final String DEFAULT_HOST = "GH-CK78P12.corp.mastercard.org";
	private static final int BUTTON_DIMEN = 20;
	private static final int BUTTON_GAP = 2;
	
	protected transient ISweeper controller;
	private transient GridSquare[][] grid;
	private transient JFrame ui;
	private transient IntLabel score;
	
	public MinesweeperUI() throws RemoteException, NotBoundException {
		String host =  JOptionPane.showInputDialog( "Select host, or cancel to run against local server", DEFAULT_HOST );
		Registry registry = LocateRegistry.getRegistry( host );
		controller = (ISweeper) registry.lookup( "Mineserver" ); 
		controller.addObserver( this );

		setupUi( controller.getCurrentFieldState(), controller.getPlayerName( this ) );
	}
	
	private void setupUi( Minefield field, String playerName ) throws RemoteException {
		Dimension d = field.getDimensions();
		
		ui = new JFrame( "Minesweeper" );
		ui.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		
		ui.addWindowListener( new WindowAdapter() {
			@Override
			public void windowClosing( WindowEvent evt ) {
				try {
					controller.deleteObserver( MinesweeperUI.this );
				}
				catch ( RemoteException e ) {
					System.err.println( "Error deregistering view; " + e.getMessage() );
				}
			}
		});
		
		JPanel gridPanel = new JPanel();
		
		gridPanel.setLayout( new GridLayout( d.height, d.width, BUTTON_GAP, BUTTON_GAP ) );
		grid = new GridSquare[ d.height ][ d.width ];
		
		for ( int j = 0; j < d.height; j++ ) {
			for ( int i = 0; i < d.width; i++ ) {
				GridSquare square = new GridSquare( j, i );
				square.setPreferredSize( new Dimension( BUTTON_DIMEN, BUTTON_DIMEN ) );
				square.addMouseListener( this );
				grid[j][i] = square;
				gridPanel.add( square );
				
				// Some squares may initially be revealed; check for this
				update( field.squareAt( j, i ) );
			}
		}
		
		JPanel topPanel = new JPanel();
		topPanel.setLayout( new FlowLayout( FlowLayout.LEFT ) );
		topPanel.add( new JLabel( playerName ) );
		topPanel.add( score = new IntLabel() );
		
		ui.setLayout( new BorderLayout() );
		ui.add( topPanel, BorderLayout.NORTH );
		ui.add( gridPanel, BorderLayout.CENTER );
		ui.pack();
		ui.setVisible( true );
	}
	
	@Override
	public void update( MineSquare square ) throws RemoteException {
		if ( square.isRevealed() ) {
			int j = square.getY();
			int i = square.getX();
			
			if ( square.hasMine() ) {
				grid[j][i].showBomb();
			}
			else {
				grid[j][i].showCount( controller.countNeighbourMines( j, i ) );
			}
			
			ui.revalidate();
		}
	}
	
	public static void main( String...args ) {
		try {
			new MinesweeperUI();
		}
		catch ( RemoteException|NotBoundException e ) {
			e.printStackTrace();
		}
	}

	@Override
	public void mousePressed( MouseEvent evt ) {
		if ( evt.getSource() instanceof GridSquare ) {
			GridSquare square = (GridSquare) evt.getSource();
			
			if ( !square.isEnabled() || square.isUncovered() ) {
				return;
			}
			
			if ( evt.getButton() == MouseEvent.BUTTON1 ) {
				try {
					controller.uncover( this, square.getYpos(), square.getXpos() );
				}
				catch ( RemoteException e ) {
					System.err.println( "Error communicating with model; " + e.getMessage() );
				}
			}
			else if ( evt.getButton() == MouseEvent.BUTTON3 ) {
				square.toggleFlag();
			}
		}
	}

	@Override public void mouseClicked( MouseEvent e ) {}	
	@Override public void mouseReleased( MouseEvent e ) {}
	@Override public void mouseEntered( MouseEvent e ) {}
	@Override public void mouseExited( MouseEvent e ) {}

	@Override
	public void message( int msg ) throws RemoteException {
		switch ( msg ) {
		case ISweeper.YOU_SCORED_A_POINT:
			score.increment();
			break;
		case ISweeper.END_OF_GAME:
			String scoreCard = controller.getFullScores();
			
			new Thread( () -> {
				JOptionPane.showMessageDialog( this.ui, scoreCard, "Game over!", JOptionPane.PLAIN_MESSAGE ); }).start();
			
		case ISweeper.YOU_STEPPED_ON_A_MINE:
			// No longer listen for clicks
			for ( GridSquare[] row : grid ) {
				for ( GridSquare square : row ) {
					square.setEnabled( false );
				}
			}
			break;
		default:
			System.err.println( "Unknown message (" + msg + ')' );
		}
	}
}
