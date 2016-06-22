package org.axp.proxy.rmi.client;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import org.axp.proxy.rmi.Pokémon;

public class ProfessorOak {	
	public static void checkPokémon() throws RemoteException, NotBoundException {
		System.out.println( "Let's see what our latest Pokémon are..." );
				
		for ( Pokémon monster : PokédexClientFactory.createLocalClient().getAll() ) {
			System.out.println( monster );
		}
		
		System.out.println( "\nHmmm, these Pokémon are even weirder than usual, TBH." );
	}
	
	public static void main( String...args ) {
		try {
			checkPokémon();
		}
		catch ( Exception e ) {
			e.printStackTrace();
		}
	}
}
