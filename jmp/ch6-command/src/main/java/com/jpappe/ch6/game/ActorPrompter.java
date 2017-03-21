package com.jpappe.ch6.game;

import com.jpappe.ch6.command.Client;
import com.jpappe.ch6.command.type.PromptActorCommand;
import com.jpappe.ch6.game.actor.Actor;

/**
 * A command producer (Client) that generates commands
 * telling an Actor to choose an action.
 * 
 * @author Jacob Pappe
 *
 */
public class ActorPrompter implements Client<PromptActorCommand> {

	/**
	 * the actor that will be prompted for an action. 
	 * This actor may change between calls to `getCommand`
	 * so the same ActorPrompter instance can generate
	 * commands for multiple actors.
	 */
	private Actor actor;
	
	/**
	 * the actors make decisions quickly so the timescale
	 * for making decisions is quite small
	 */
	private static final int TIMESCALE = 10;
	
	/**
	 * we use this reference to read current game world state
	 */
	private final GameEngine game;
	
	public ActorPrompter(GameEngine gameEngine) {
		game = gameEngine;
	}
	
	public void setActor(Actor actor) {
		this.actor = actor;
	}

	
	@Override
	public PromptActorCommand getCommand() {
		
		PromptActorCommand command = new PromptActorCommand(actor);
		
		float timeGap = actor.getSpeed() / TIMESCALE;
		command.setTime(game.getSchedule().getCurrentTime() + Math.round(timeGap));
		
		return command;
	}

}
