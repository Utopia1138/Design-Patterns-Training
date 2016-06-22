package org.axp.proxy.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Set;

public interface Pokédex extends Remote {
	/**
	 * Check that the Pokédex is online
	 * @throws RemoteException if not online
	 */
	public void checkOnline() throws RemoteException;
	
	/**
	 * Do we know about the current Pokémon
	 * @param monster some Pokémon
	 * @return true iff this Pokémon has been registered in the Pokédex before
	 * @throws RemoteException if not online
	 */
	public boolean isKnown( Pokémon monster ) throws RemoteException;
	
	/**
	 * Add a new Pokémon to the Pokédex 
	 * @param monster the Pokémon to add
	 * @return true if it was successfully added, or false if it already exists
	 * @throws RemoteException if not online
	 */
	public boolean addNew( Pokémon monster ) throws RemoteException;
	
	/**
	 * Update a Pokémon's details in the Pokédex. Note that you cannot change the Pokémon's name.
	 * @param monster the Pokémon to update
	 * @return true if the entry was updated successfully, false if it does not exist
	 * @throws RemoteException if not online
	 */
	public boolean update( Pokémon monster ) throws RemoteException;
	
	/**
	 * List all known Pokémon
	 * @return a collection of Pokémon
	 * @throws RemoteException if not online
	 */
	public Set<Pokémon> getAll() throws RemoteException;
}
