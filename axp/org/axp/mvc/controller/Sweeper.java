package org.axp.mvc.controller;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;

import org.axp.mvc.model.MineSquare;
import org.axp.mvc.model.Minefield;
import org.axp.mvc.model.MinesweeperGame;
import org.axp.mvc.model.IMinesweeperGame;
import org.axp.mvc.model.Player;
import org.axp.mvc.rmi.RemoteObservable;
import org.axp.mvc.rmi.RemoteObserver;

public class Sweeper extends RemoteObservable<MineSquare> implements ISweeper {
	private IMinesweeperGame model;
	private HashMap<RemoteObserver<MineSquare>, Player> players = new HashMap<>();
	
	public Sweeper( IMinesweeperGame model ) {
		this.model = model;
	}
	
	public void run() throws RemoteException {
		ISweeper stub = (ISweeper) UnicastRemoteObject.exportObject( this, 0 );
		Registry registry = LocateRegistry.getRegistry();
		registry.rebind( "Mineserver", stub );
		System.out.println( "Created model, waiting for views" );
	}
	
	@Override
	public synchronized void uncover( RemoteObserver<MineSquare> client, int ypos, int xpos ) throws RemoteException {
		model.reveal( ypos, xpos );
		MineSquare square = model.squareAt( ypos, xpos );
		notifyObservers( square );
		
		if ( square.hasMine() ) {
			model.killPlayer( players.get( client ) );
			client.message( ISweeper.YOU_STEPPED_ON_A_MINE );
		}
		else {
			model.addPoint( players.get( client ) );
			client.message( ISweeper.YOU_SCORED_A_POINT );
		}
		
		if ( model.isCleared() ) {
			for ( RemoteObserver<MineSquare> obs : players.keySet() ) {
				obs.message( ISweeper.END_OF_GAME );
			}
		}
	}

	@Override
	public int countNeighbourMines( int ypos, int xpos ) {
		return model.countNeighbourMines( ypos, xpos );
	}
	
	public static void main( String...args ) {
		try {
			Minefield field = new Minefield( 16, 24, 40 );
			new Sweeper( new MinesweeperGame( field ) ).run();
		}
		catch ( RemoteException e ) {
			e.printStackTrace();
		}
	}

	@Override
	public Minefield getCurrentFieldState() {
		return this.model.getField();
	}
	
	@Override
	public void addObserver( RemoteObserver<MineSquare> obs ) throws RemoteException {
		super.addObserver( obs );
		players.put( obs, model.newPlayer() );
	}
	
	@Override
	public void deleteObserver( RemoteObserver<MineSquare> obs ) throws RemoteException {
        super.deleteObserver( obs );
        players.remove( obs );
	}

	@Override
	public String getPlayerName( RemoteObserver<MineSquare> obs ) {
		return players.get( obs ).getName();
	}

	@Override
	public String getFullScores() {
		StringBuilder sb = new StringBuilder();
		
		for ( Player p : model.getScores() ) {
			sb.append( '\n' ).append( p.getScore() ).append( "   " ).append( p.getName() );
			
			if ( p.isDead() ) {
				sb.append( "   (DEAD)" );
			}
		}
		
		return sb.substring( 1 );
	}

	@Override
	public void checkOnline() {
		/* Do nothing */
	}    
}
