package org.axp.proxy.rmi.client;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import org.axp.proxy.rmi.Pokédex;

public class PokédexClientFactory {
	public static Pokédex createClient( String host ) throws RemoteException, NotBoundException {
		Registry registry = LocateRegistry.getRegistry( host );
		return (Pokédex) registry.lookup( "Pokédex" );
	}
	
	public static Pokédex createLocalClient() throws RemoteException, NotBoundException {
		return createClient( null );
	}
}
