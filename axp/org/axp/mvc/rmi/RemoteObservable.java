package org.axp.mvc.rmi;

import java.rmi.RemoteException;
import java.util.HashSet;
import java.util.Set;

public abstract class RemoteObservable<T> {
       private Set<RemoteObserver<T>> observers = new HashSet<>();

       public void addObserver( RemoteObserver<T> obs ) throws RemoteException {
               System.out.println( "Adding new observer; " + obs );
               observers.add( obs );
       }

       public void deleteObserver( RemoteObserver<T> obs ) throws RemoteException {
               System.out.println( "Removing observer; " + obs );
               observers.remove( obs );
       }

       protected void notifyObservers( T messageObject ) {
               for ( RemoteObserver<T> obs : new HashSet<RemoteObserver<T>>( observers ) ) {
                       try {
                               obs.update( messageObject );
                       }
                       catch ( RemoteException e ) {
                               System.err.println( "Error updating observer; removing" );
                               observers.remove( obs );
                       }
               }
       }
}