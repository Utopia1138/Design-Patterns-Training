package com.jpappe.ch6.game;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

import com.jpappe.ch6.command.Command;
import com.jpappe.ch6.command.TimeBasedCommand;
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
public class GameEngine implements Invoker<TimeBasedCommand> {

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
	 * This keeps track of time in the game and is responsible
	 * for making events go in order of their relative time.
	 */
	private GameScheduler<TimeBasedCommand> schedule;
	
	
	/**
	 * Keeps track of all actors in the game
	 */
	private List<Actor> actors;
	
	
	/**
	 * The coordinate system of the game
	 */
	private GameMap gameMap;
	
	
	
	public GameEngine() {
		schedule = new GameScheduler<TimeBasedCommand>();
		currentState = GameState.NOT_STARTED;
		actors = new ArrayList<Actor>();
	}
	
	/**
	 * Adds an actor to the game on the specified team
	 * @param team
	 * @param actor
	 */
	public void addActor(Actor actor) {
		actors.add(actor);
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
		
		TimeBasedCommand currentCommand;
		while( !this.isGameOver() ) {
			// pull the next command off the event queue and execute it.
			currentCommand = schedule.next();
			currentCommand.execute();
		}
		
		// the game is over
		currentState = GameState.FINISHED;
	}


	/**
	 * Determines whether the game is over. This just means
	 * that there's only a single team left.
	 * 
	 * @return
	 */
	private boolean isGameOver() {
		String t = null;
		/**
		 * this is not the most efficient way to
		 * do this check, but whatever
		 */
		for(Actor a : actors) {
			if ( !a.isDisabled() ) {
				if ( t == null ) {
					t = a.getTeam();
				}
				else {
					if ( !a.getTeam().equals(t) ) {
						/**
						 * we've found more than one team with active
						 * actors, so the game's not over
						 */
						return false;
					}
				}
			}
		}
		/**
		 * we've gone through all actors and haven't found
		 * more than one active team, so there must be a winner
		 */
		return true;
	}

	/**
	 * Get the game into a state where it can run. This
	 * involves building up the first round of events by
	 * having each actor decide what action to perform
	 */
	private void initialise() {
		
		/**
		 * go through each actor and generate a prompt event
		 * to put them into action
		 */
		final ActorPrompter prompter = new ActorPrompter(this);
		actors.forEach(a -> {
			prompter.setActor(a);
			this.schedule.addEvent(prompter.getCommand());
		});
		
	}



	public List<Actor> getActors() {
		return actors;
	}

	public GameMap getGameMap() {
		return gameMap;
	}

	public GameScheduler<TimeBasedCommand> getSchedule() {
		return schedule;
	}

	@Override
	public void setCommand(TimeBasedCommand c) {
		schedule.addEvent(c);
	}

}
