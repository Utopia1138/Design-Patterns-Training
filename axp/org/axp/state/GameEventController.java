package org.axp.state;

import java.io.IOException;
import java.util.Observable;
import java.util.TreeMap;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.axp.state.entity.Game;
import org.axp.state.states.InitialState;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

public class GameEventController extends Observable {
	private XMLReader xmlReader;
	
	private TreeMap<String, String> users = new TreeMap<>();
	private TreeMap<String, Game> games = new TreeMap<>();
	
	public void parse( InputSource source ) throws ParserConfigurationException, SAXException, IOException {
		SAXParserFactory factory = SAXParserFactory.newInstance(); 
		SAXParser saxParser = factory.newSAXParser();
		this.xmlReader = saxParser.getXMLReader();
		
		setState( new InitialState( this ) );
		this.xmlReader.parse( source );
	}
	
	private void notifyUi( String changed ) {
		setChanged();
		notifyObservers( changed );
	}
	
	public void setState( XMLState state ) {
		notifyUi( "STATE:" + state.getClass().getSimpleName() );
		this.xmlReader.setContentHandler( state );
	}
	
	public void connect() {
		notifyUi( "CONNECTED" );
	}
	
	public void disconnect() {
		notifyUi( "DISCONNECTED" );
	}
	
	public void finish() {
		notifyUi( "FINISHED" );
	}
	
	public void updateUsers() {
		notifyUi( "USERS" );
	}

	public void addUser( String userId, String userName ) {
		this.users.put( userId, userName );
		updateUsers();
	}

	public void removeUser( String userId ) {
		String user = this.users.remove( userId );
		
		if ( user == null ) {
			throw new IllegalArgumentException( "No user with id " + userId );
		}
		
		updateUsers();
	}

	public String[] getUserList() {
		return this.users.values().toArray( new String[] {} );
	}

	public String getUser( String userId ) {
		return this.users.get( userId );
	}
	
	public void updateGames() {
		notifyUi( "GAMES" );
	}

	public void addGame( String gameId, Game game ) {
		this.games.put( gameId, game );
		updateGames();
	}

	public void removeGame( String gameId ) {
		Game game = this.games.remove( gameId );
		
		if ( game == null ) {
			throw new IllegalArgumentException( "No game with id " + gameId );
		}
		
		updateGames();
	}

	public Game[] getGameList() {
		return this.games.values().toArray( new Game[] {} );
	}
}
