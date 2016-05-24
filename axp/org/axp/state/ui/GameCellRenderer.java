package org.axp.state.ui;

import java.awt.Color;
import java.awt.Component;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

import org.axp.state.entity.Game;

public class GameCellRenderer implements ListCellRenderer<Game> {
	private static final Color ALICE_BLUE = new Color( 0xf0f8ff );
	
	private DefaultListCellRenderer baseRenderer = new DefaultListCellRenderer();
	
	public static String toHtml( Game g ) {
		StringBuilder sb = new StringBuilder( "<html>" ).append( g.getGameType() );
		
		for ( String p : g.getPlayers() ) {
			sb.append( "<br>&nbsp;&nbsp;" ).append( p );
		}
		
		for ( String rule : g.getRules() ) {
			sb.append( "<br>&nbsp;&nbsp;" ).append( rule );
		}
		
		return sb.append( "</html>" ).toString();
	}

	@Override
	public Component getListCellRendererComponent( JList<? extends Game> list,
			Game value, int index, boolean isSelected, boolean cellHasFocus ) {
		
		Component label = baseRenderer.getListCellRendererComponent( list, toHtml( value ), index, isSelected, cellHasFocus );
		
		if ( !isSelected && label.isEnabled() && ( index % 2 == 1 ) ) {
			label.setBackground( ALICE_BLUE );
		}
		
		return label;
	}
}
