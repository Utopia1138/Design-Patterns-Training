package org.axp.mvc.view;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;

import org.axp.mvc.controller.ISweeper;
import org.axp.mvc.controller.Sweeper;
import org.axp.mvc.model.MineSquare;
import org.axp.mvc.model.Minefield;

public class MinesweeperUI implements Observer, MouseListener {
	private static final long serialVersionUID = -6043526207968115429L;
	private static final int BUTTON_DIMEN = 20;
	private static final int BUTTON_GAP = 2;
	
	protected ISweeper controller;
	private transient GridSquare[][] grid;
	private transient JFrame ui;
	
	public MinesweeperUI( ISweeper controller, Dimension dimen ) {
		this.controller = controller;
		controller.addObserver( this );
		
		setupUi( (int) dimen.getHeight(), (int) dimen.getWidth() );
	}
	
	private void setupUi( int rows, int cols ) {
		ui = new JFrame( "Minesweeper" );
		ui.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		
		ui.addWindowListener( new WindowAdapter() {
			@Override
			public void windowClosing( WindowEvent e ) {
				controller.deleteObserver( MinesweeperUI.this );
			}
		});
		
		ui.setLayout( new GridLayout( rows, cols, BUTTON_GAP, BUTTON_GAP ) );
		grid = new GridSquare[ rows ][ cols ];
		
		for ( int j = 0; j < rows; j++ ) {
			for ( int i = 0; i < cols; i++ ) {
				GridSquare square = new GridSquare( j, i );
				square.setPreferredSize( new Dimension( BUTTON_DIMEN, BUTTON_DIMEN ) );
				square.addMouseListener( this );
				grid[j][i] = square;
				ui.add( square );
			}
		}
		
		ui.pack();
		ui.setVisible( true );
	}
	
	private void updateUi( MineSquare square ) {
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

	@Override
	public void update( Observable o, Object arg ) {
		if ( controller == o ) {
			if ( arg instanceof MineSquare ) {
				updateUi( (MineSquare) arg );
			}
		}
	}
	
	public static void main( String...args ) {
		Minefield model = new Minefield( 16, 24, 40 );
		Sweeper controller = new Sweeper( model );
		new MinesweeperUI( controller, new Dimension( 24, 16 ) );
	}

	@Override
	public void mousePressed( MouseEvent e ) {
		if ( e.getSource() instanceof GridSquare ) {
			GridSquare square = (GridSquare) e.getSource();
			
			if ( e.getButton() == MouseEvent.BUTTON1 ) {
				controller.uncover( square.getYpos(), square.getXpos() );
			}
			else if ( e.getButton() == MouseEvent.BUTTON3 ) {
				square.toggleFlag();
			}
		}
	}

	@Override public void mouseClicked( MouseEvent e ) {}	
	@Override public void mouseReleased( MouseEvent e ) {}
	@Override public void mouseEntered( MouseEvent e ) {}
	@Override public void mouseExited( MouseEvent e ) {}
}
