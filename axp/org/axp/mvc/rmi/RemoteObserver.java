package org.axp.mvc.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RemoteObserver<T> extends Remote {
	void update( T updateObj ) throws RemoteException;
}
