package org.axp.state.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;

import org.axp.state.GameEventController;
import org.axp.state.entity.Game;

public class ClientView extends JFrame implements Observer {
	private static final long serialVersionUID = -8864927050557607120L;
	
	private JLabel status = new JLabel( "Connecting..." );
	private JList<String> userList = new JList<>();
	private JList<Game> gameList = new JList<>();
	private JLabel parseState = new JLabel();
	
	public ClientView( GameEventController controller ) {
		super( "Dashboard" );
		setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		
		this.userList.setEnabled( false );
		this.gameList.setEnabled( false );
		this.gameList.setCellRenderer( new GameCellRenderer() );
		this.parseState.setFont( this.parseState.getFont().deriveFont( Font.PLAIN ) );
		this.parseState.setForeground( Color.DARK_GRAY );
		
		Container pane = getContentPane();
		pane.setLayout( new BorderLayout() );
		
		pane.add( this.status, BorderLayout.NORTH );
		pane.add( wrap( this.userList ), BorderLayout.WEST );
		pane.add( wrap( this.gameList ), BorderLayout.EAST );
		pane.add( this.parseState, BorderLayout.SOUTH );
		
		pack();
		
		controller.addObserver( this );
	}
	
	private static JScrollPane wrap( JList<?> list ) {
		JScrollPane listScroller = new JScrollPane( list );
		listScroller.setPreferredSize( new Dimension( 200, 200 ) );
		return listScroller;
	}

	@Override
	public void update( Observable o, Object change ) {
		if ( "FINISHED".equals( change ) ) {
			System.exit( 0 );
		}
		else if ( "DISCONNECTED".equals( change ) ) {
			this.userList.setEnabled( false );
			this.gameList.setEnabled( false );
			this.status.setText( "Disconnected" );
		}
		else if ( "CONNECTED".equals( change ) ) {
			this.userList.setEnabled( true );
			this.gameList.setEnabled( true );
			this.status.setText( "Connected" );
		}
		else if ( "USERS".equals( change ) ) {
			GameEventController controller = (GameEventController) o;
			this.userList.setListData( controller.getUserList() );
		}
		else if ( "GAMES".equals( change ) ) {
			GameEventController controller = (GameEventController) o;
			this.gameList.setListData( controller.getGameList() );
		}
		else if ( change != null && change.toString().startsWith( "STATE:" ) ) {
			this.parseState.setText( change.toString().substring( "STATE:".length() ) );
		}
	}
}
