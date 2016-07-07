package org.axp.mvc.controller;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;

import org.axp.mvc.model.MineSquare;
import org.axp.mvc.model.Minefield;
import org.axp.mvc.model.MinesweeperModel;
import org.axp.mvc.model.Player;
import org.axp.mvc.rmi.RemoteObservable;
import org.axp.mvc.rmi.RemoteObserver;

public class Sweeper extends RemoteObservable<MineSquare> implements MinesweeperController {
	private MinesweeperModel model;
	private HashMap<RemoteObserver<MineSquare>, Player> players = new HashMap<>();
	
	public Sweeper( MinesweeperModel model ) {
		this.model = model;
	}
	
	public void run() throws RemoteException {
		MinesweeperController stub = (MinesweeperController) UnicastRemoteObject.exportObject( this, 0 );
		Registry registry = LocateRegistry.getRegistry();
		registry.rebind( "Mineserver", stub );
		System.out.println( "Created model, waiting for views" );
	}
	
	public synchronized boolean uncover( int ypos, int xpos ) {
		model.reveal( ypos, xpos );
		MineSquare square = model.squareAt( ypos, xpos );
		notifyObservers( square );
		return !square.hasMine();
	}

	@Override
	public int countNeighbourMines( int ypos, int xpos ) {
		return model.countNeighbourMines( ypos, xpos );
	}
	
	public static void main( String...args ) {
		try {
			new Sweeper( new Minefield( 16, 24, 40 ) ).run();
		}
		catch ( RemoteException e ) {
			e.printStackTrace();
		}
	}

	@Override
	public MinesweeperModel getCurrentFieldState() {
		return this.model;
	}
	
	@Override
	public void addObserver( RemoteObserver<MineSquare> obs ) throws RemoteException {
		super.addObserver( obs );
		
		Player player;
		do {
			player = new Player();
		}
		while ( players.containsValue( player ) );
		
		players.put( obs, player );
	}
	
	@Override
	public void deleteObserver( RemoteObserver<MineSquare> obs ) throws RemoteException {
        super.deleteObserver( obs );
        players.remove( obs );
	}    
}
