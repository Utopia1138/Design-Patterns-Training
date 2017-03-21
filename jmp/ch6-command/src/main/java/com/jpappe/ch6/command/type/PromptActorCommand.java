package com.jpappe.ch6.command.type;

import com.jpappe.ch6.command.TimeBasedCommand;
import com.jpappe.ch6.game.actor.Actor;

public class PromptActorCommand extends TimeBasedCommand {

	private Actor actor;
	
	public PromptActorCommand(Actor actor) {
		this.actor = actor;
	}

	@Override
	public void execute() {
		// TODO

	}

	@Override
	public void undo() {
		// TODO Auto-generated method stub

	}

}
