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

/**
 * This is the main client that parses a stream of XML using the SAX Parser. In State Pattern terms, this
 * is the <em>Context</em> class; the {@link XMLState} class is the main <em>State</em> interface (actually
 * an abstract class), and each of the classes in the <tt>org.axp.state.states</tt> package is a
 * <em>Concrete State</em>.
 * 
 * Each of the states is also a {@link ContentHandler}; the SAX {@link XMLReader} allows these to be swapped
 * in and out as necessary, making it ideal for adaption to the state pattern.
 */
public class GameEventController extends Observable {
	private XMLReader xmlReader;
	
	private TreeMap<String, String> users = new TreeMap<>();
	private TreeMap<String, Game> games = new TreeMap<>();
	
	/**
	 * Parse an XML document
	 * 
	 * @param source any input source; e.g. an open file or <tt>System.in</tt>
	 * @throws ParserConfigurationException hopefully never
	 * @throws SAXException if the XML document was malformed
	 * @throws IOException if the input source could not be read
	 */
	public void parse( InputSource source ) throws ParserConfigurationException, SAXException, IOException {
		SAXParserFactory factory = SAXParserFactory.newInstance(); 
		SAXParser saxParser = factory.newSAXParser();
		this.xmlReader = saxParser.getXMLReader();
		
		setState( new InitialState( this ) );
		this.xmlReader.parse( source );
	}
	
	/**
	 * Notify all observers of some change
	 * @param changed a string describing the change
	 */
	private void notifyUi( String changed ) {
		setChanged();
		notifyObservers( changed );
	}
	
	/**
	 * Enter a new parse state
	 * @param state
	 */
	public void setState( XMLState state ) {
		notifyUi( "STATE:" + state.getClass().getSimpleName() );
		this.xmlReader.setContentHandler( state );
	}
	
	/** Connected to server - notify observers */
	public void connect() {
		notifyUi( "CONNECTED" );
	}
	
	/** Disconnected from server - notify observers */
	public void disconnect() {
		notifyUi( "DISCONNECTED" );
	}
	
	/** Finished parsing - notify observers */
	public void finish() {
		notifyUi( "FINISHED" );
	}
	
	/** List of users has changed - notify observers */
	public void updateUsers() {
		notifyUi( "USERS" );
	}

	/**
	 * Add a new user to the list
	 * @param userId internal ID
	 * @param userName display name
	 */
	public void addUser( String userId, String userName ) {
		this.users.put( userId, userName );
		updateUsers();
	}

	/**
	 * Remove an existing user after logoff
	 * @param userId internal ID
	 */
	public void removeUser( String userId ) {
		String user = this.users.remove( userId );
		
		if ( user == null ) {
			throw new IllegalArgumentException( "No user with id " + userId );
		}
		
		updateUsers();
	}

	/**
	 * Get a list of current user names
	 * @return array of display names
	 */
	public String[] getUserList() {
		return this.users.values().toArray( new String[] {} );
	}

	/**
	 * Get a specific user's name
	 * @param userId internal ID
	 * @return user's display name
	 */
	public String getUser( String userId ) {
		return this.users.get( userId );
	}
	
	/** List of games has changed - notify observers */
	public void updateGames() {
		notifyUi( "GAMES" );
	}

	/**
	 * Add a new game to the list
	 * @param gameId internal ID
	 * @param game game details
	 */
	public void addGame( String gameId, Game game ) {
		this.games.put( gameId, game );
		updateGames();
	}

	/**
	 * Mark a game as finished
	 * @param gameId internal ID
	 * @param winnerId winner of the game, or null if tied
	 */
	public void finishGame( String gameId, String winnerId ) {
		Game game = this.games.get( gameId );
		
		if ( game == null ) {
			throw new IllegalArgumentException( "No game with id " + gameId );
		}
		
		String winner = null;
		
		if ( winnerId != null ) {
			winner = this.users.get( winnerId );
			
			if ( winner == null ) {
				throw new IllegalArgumentException( "No player with id " + winnerId );
			}
		}
		
		game.setFinished( winner );
		updateGames();
	}

	/**
	 * Get a list of current and finished games
	 * @return array of {@link Game} objects
	 */
	public Game[] getGameList() {
		return this.games.values().toArray( new Game[] {} );
	}
}
