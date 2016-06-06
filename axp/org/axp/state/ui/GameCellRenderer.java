package org.axp.state.ui;

import java.awt.Color;
import java.awt.Component;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

import org.axp.state.entity.Game;

/**
 * Another boring UI class -- this renders games in multiline HTML.
 * 
 * (An example of the adaptor pattern, though, for those keeping score...)
 */
public class GameCellRenderer implements ListCellRenderer<Game> {
	private static final Color ALICE_BLUE = new Color( 0xf0f8ff );
	
	private DefaultListCellRenderer baseRenderer = new DefaultListCellRenderer();
	
	public static String toHtml( Game g ) {
		StringBuilder sb = new StringBuilder( "<html>" ).append( g.getGameType() );
		
		if ( g.isFinished() ) {
			sb.append( "<i>" ).append( " - COMPLETE" );
		}
		
		for ( String p : g.getPlayers() ) {
			sb.append( "<br>&nbsp;&nbsp;" ).append( p );
			
			if ( p.equals( g.getWinner() ) ) {
				sb.append( " - WINNER" );
			}
		}
		
		for ( String rule : g.getRules() ) {
			sb.append( "<br>&nbsp;&nbsp;" ).append( rule );
		}
		
		if ( g.isFinished() ) {
			sb.append( "</i>" );
		}
		
		return sb.append( "</html>" ).toString();
	}

	@Override
	public Component getListCellRendererComponent( JList<? extends Game> list,
			Game game, int index, boolean isSelected, boolean cellHasFocus ) {
		
		Component label = baseRenderer.getListCellRendererComponent( list, toHtml( game ), index, isSelected, cellHasFocus );

		if ( !isSelected && label.isEnabled() && ( index % 2 == 1 ) ) {
			label.setBackground( ALICE_BLUE );
		}
		
		return label;
	}
}
