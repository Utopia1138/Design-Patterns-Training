package com.jpappe.ch6.game.actor;

import com.jpappe.ch6.command.Client;
import com.jpappe.ch6.command.TimeBasedCommand;
import com.jpappe.ch6.command.Receiver;


/**
 * This object represents anything that can perform actions
 * in the game.
 * 
 * Each actor has its own speed, which indicates how quickly
 * it can perform actions.
 * 
 * An actor also has its own Client, which is responsible for
 * generating Command objects.
 * 
 * @author Jacob Pappe
 *
 */
public abstract class Actor implements Receiver, Comparable<Actor> {
	
	protected Client<TimeBasedCommand> client;
	
	protected int speed;
	protected String team;
	
	public int compareTo(Actor other) {
		return (this.speed - other.speed);
	}
	
	public Client<TimeBasedCommand> getClient() {
		return client;
	}
	
	public int getSpeed() {
		return speed;
	}

	public String getTeam() {
		return team;
	}
	
	public abstract boolean isDisabled();
}
