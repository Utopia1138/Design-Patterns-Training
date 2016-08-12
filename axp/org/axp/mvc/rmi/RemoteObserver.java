package org.axp.mvc.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RemoteObserver<T> extends Remote {
	/**
	 * Update with an object that the observer is looking for
	 * @param updateObj some object that the observer is interested in
	 * @throws RemoteException if a communication error occurs
	 */
	void update( T updateObj ) throws RemoteException;
	
	/**
	 * Update with an integer message; this is used to indicate change in state
	 * @param msg an int signal with pre-arranged meaning between client and server
	 * @throws RemoteException if a communication error occurs
	 */
	void message( int msg ) throws RemoteException;
}
