package com.jpappe.ch6.game;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

import com.jpappe.ch6.command.Command;
import com.jpappe.ch6.command.ComparableCommand;
import com.jpappe.ch6.command.Invoker;
import com.jpappe.ch6.game.actor.Actor;
import com.jpappe.ch6.game.map.GameMap;

/**
 * The GameEngine keeps track of everything in the game and
 * is responsible for executing all the events of the game.
 * 
 * @author Jacob Pappe
 *
 */
public class GameEngine implements Invoker<ComparableCommand> {

	/**
	 * enumerates the possible states are game
	 * might be in.
	 */
	private enum GameState {
		NOT_STARTED,
		IN_PROGRESS,
		FINISHED
	};
	
	/**
	 * Keeps track of the game's current state (obvs)
	 */
	private GameState currentState;
	
	/**
	 * This is our event queue, which keeps track of
	 * all the future events in the game in "priority"
	 * order, where a command's priority corresponds to 
	 * when in the timeline of the game it occurs. A lower
	 * priority command will be executed sooner than a higher
	 * priority command
	 */
	private PriorityQueue<ComparableCommand> commandQueue;
	
	/**
	 * Keeps track of all actors in the game, grouped by 
	 * team name. Ultimately, the game ends when a single
	 * team remains.
	 */
	private Map<String, List<Actor>> actors;
	
	/**
	 * The coordinate system of the game
	 */
	private GameMap gameMap;
	
	public GameEngine() {
		commandQueue = new PriorityQueue<ComparableCommand>();
		currentState = GameState.NOT_STARTED;
		actors = new HashMap<String,List<Actor>>();
	}
	
	/**
	 * Adds an actor to the game on the specified team
	 * @param team
	 * @param actor
	 */
	public void addActor(String team, Actor actor) {
		if ( actors.get(team) == null ) {
			actors.put(team, new ArrayList<Actor>() );
		}
		actors.get(team).add(actor);
	}
	
	/**
	 * add the game map to the game (as long as the
	 * game hasn't yet started).
	 * @param map
	 */
	public void setGameMap(GameMap map) {
		if(this.currentState != GameState.NOT_STARTED) {
			throw new IllegalStateException("Cannot set the game map after the game has started!");
		}
		this.gameMap = map;
	}
	
	public void runGame() {
		if(this.currentState != GameState.NOT_STARTED) {
			throw new IllegalStateException("The game has already started!");
		}
		
		currentState = GameState.IN_PROGRESS;
		
		// get everything ready to run
		initialise();
		
		Command currentCommand;
		while( !this.isGameOver() ) {
			// pull the next command off the event queue and execute it.
			currentCommand = commandQueue.poll();
			currentCommand.execute();
		}
		
		// the game is over
		currentState = GameState.FINISHED;
	}


	/**
	 * Get the game into a state where it can run. This
	 * involves building up the first round of events by
	 * having each actor decide what action to perform
	 */
	private void initialise() {
		
		/**
		 * we want a priority queue where the actor with the highest
		 * speed goes first
		 */
		final PriorityQueue<Actor> actorQueue = new PriorityQueue<Actor>(
				(a1, a2) -> {
					return a2.compareTo(a1);
				} );
		
		actors.keySet().forEach(t -> {
			actors.get(t).forEach(a -> {
				actorQueue.add(a);
			});
		});
		
		/**
		 * TODO: create a Command for asking each Actor
		 * to choose an action
		 */
	}

	public void setCommand(ComparableCommand c) {
		commandQueue.add(c);
	}

	public PriorityQueue<ComparableCommand> getCommandQueue() {
		return commandQueue;
	}

	public Map<String, List<Actor>> getActors() {
		return actors;
	}

	public GameMap getGameMap() {
		return gameMap;
	}

}
