# Design Patterns - Chapter 2: Observer Pattern

This application generates a random terrain
and allows the user to drop rolling marbles 
onto it. The camera can cycle between free-flying
mode or chasing individual marbles.

## Observer Pattern

The `InputListener` interface hierarchy is an example
of the Observer pattern in that a Listener is registered
with specific input events, and when those events occur
the listeners are notified. Specific examples in this 
app are:

   * **MarbleDropper**: When this listener's event is triggered 
   (and the key is no longer pressed), a marble is created and
   dropped from a height onto the terrain.
   * **CameraSwitchListener**: When this listener's event
   is triggered, the camera is cycled between "free-fly" mode
   and following a marble. This listener allows the user to 
   follow each marble, and once they've cycled past the last one
   the camera returns to free-fly mode.

## Running the app

This is a Maven app, so if you have Maven installed you can
just run:

```
mvn exec:java -Dexec.mainClass="org.jmp.observer.RollingMarbles"
```