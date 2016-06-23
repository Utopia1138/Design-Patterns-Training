package org.axp.proxy.rmi.server;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashSet;
import java.util.Observable;
import java.util.Set;

import org.axp.proxy.rmi.Pokédex;
import org.axp.proxy.rmi.Pokémon;

public class PokédexServer extends Observable implements Pokédex {
	private HashSet<Pokémon> deck = new HashSet<>();
	
	public PokédexServer() {}
	
	@Override
	public void checkOnline() throws RemoteException {
		/* Do nothing */
	}
	
	@Override
	public boolean isKnown( Pokémon monster ) {
		return deck.contains( monster );
	}
	
	private void updateUi( Pokémon monster ) {
		setChanged();
		notifyObservers( monster );
	}
	
	@Override
	public synchronized boolean addNew( Pokémon monster ) {
		if ( isKnown( monster ) ) {
			return false;
		}
		
		deck.add( monster );
		System.out.println( "Added new Pokémon " + monster.getName() );
		updateUi( monster );
		return true;
	}
	
	@Override
	public synchronized boolean update( Pokémon monster ) {
		if ( !isKnown( monster ) ) {
			return false;
		}
		
		deck.add( monster );
		System.out.println( "Updated Pokémon " + monster.getName()  );
		updateUi( monster );
		return true;
	}

	@Override
	public Set<Pokémon> getAll() {
		return deck;
	}
	
	public void run() throws RemoteException {
		Pokédex stub = (Pokédex) UnicastRemoteObject.exportObject( this, 0 );
		Registry registry = LocateRegistry.getRegistry();
		registry.rebind( "Pokédex", stub );
		System.out.println( "Waiting for new Pokémon" );
	}

	public static void main( String...args ) {
		try {
			new PokédexServer().run();
		}
		catch ( Exception e ) {
			e.printStackTrace();
		}
	}
}
