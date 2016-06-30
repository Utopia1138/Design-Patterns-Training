package org.axp.mvc.view;

import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;

public class GridSquare extends JLabel {
	private static final long serialVersionUID = 1640490291433636521L;
	
	
	private static enum GridSquareState {
		UNMARKED, FLAGGED, UNCOVERED
	}

	private final int ypos;
	private final int xpos;
	private GridSquareState state = GridSquareState.UNMARKED;
	
	public GridSquare( int ypos, int xpos ) {
		super( "", SwingConstants.CENTER );
		setFont( new Font( "Consolas", Font.PLAIN, 16 ) );
		setBorder( BorderFactory.createBevelBorder( BevelBorder.RAISED ) );
		this.ypos = ypos;
		this.xpos = xpos;
	}
	
	public int getYpos() {
		return ypos;
	}
	
	public int getXpos() {
		return xpos;
	}
	
	public void showBomb() {
		setText( "" );
		setIcon( new ImageIcon( "axp/org/axp/mvc/img/mine.png" ) );
		setBorder( BorderFactory.createEmptyBorder() );
	}
	
	public void showCount( int c ) {
		state = GridSquareState.UNCOVERED;
		setText( ( c == 0 ) ? "" : Integer.toString( c ) );
		setBorder( BorderFactory.createEmptyBorder() );
	}
	
	public void toggleFlag() {
		switch ( state ) {
		case UNMARKED:
			setIcon( new ImageIcon( "axp/org/axp/mvc/img/flag.png" ) );
			state = GridSquareState.FLAGGED;
			break;
		case FLAGGED:
			setIcon( null );
			state = GridSquareState.UNMARKED;
			break;
		default:
			// Ignore if uncovered
		}
	}
}
