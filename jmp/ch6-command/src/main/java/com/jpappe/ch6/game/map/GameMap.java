package com.jpappe.ch6.game.map;

import java.util.HashMap;
import java.util.Map;

import com.jpappe.ch6.game.actor.Actor;

/**
 * A simple representation of the map of a game. This is really just a simple 2d
 * coordinate system.
 * 
 * @author Jacob Pappe
 *
 */
public class GameMap {

	/**
	 * Rather than keep track of a whole map, just keep track of where
	 * individual people are in the coordinate system.
	 */
	private Map<Actor, MapCoordinates> actorToCoordinates;

	/**
	 * And a reverse map so we can perform lookups in both directions
	 */
	private Map<MapCoordinates, Actor> coordinatesToActor;

	/**
	 * Our game map will be a square, so we just need to know the maximum
	 * height/width
	 */
	private final int boundary;

	public GameMap(int maxWidth) {
		actorToCoordinates = new HashMap<Actor, MapCoordinates>();
		coordinatesToActor = new HashMap<MapCoordinates, Actor>();
		boundary = maxWidth;
	}

	/**
	 * put an Actor on the Map
	 * 
	 * @param actor
	 * @param x
	 * @param y
	 */
	public boolean moveActor(Actor actor, int x, int y) {
		
		// are we trying to move outside the map?
		if (x >= boundary || y >= boundary) {
			System.out.println(String.format(
					"Coordinates (%d, %d) outside boundary of map (%d)", x, y,
					boundary));
			return false;
		}
		
		MapCoordinates c = new MapCoordinates(x, y);
		
		// are we trying to move onto an occupied space?
		if ( coordinatesToActor.get(c) != null && coordinatesToActor.get(c) != actor ) {
			System.out.println(
					String.format("Cannot place Actor '%s' at coordinates (%d, %d); it is currently occupied by Actor '%s'",
							actor.getName(),
							x,
							y,
							coordinatesToActor.get(c).getName()
							) );
			return false;
		}
		
		// if the actor's already on the map, move them off their old space
		if(actorToCoordinates.get(actor) != null ) {
			coordinatesToActor.remove( actorToCoordinates.get(actor) );
		}
		
		// put them in their new coordinates
		actorToCoordinates.put(actor, c);
		coordinatesToActor.put(c, actor);
		
		return true;
	}

}
