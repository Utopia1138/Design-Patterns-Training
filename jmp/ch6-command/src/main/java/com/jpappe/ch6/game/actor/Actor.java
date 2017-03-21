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
public class Actor implements Receiver, Comparable<Actor> {
	
	/**
	 * This is the agent that's responsible for generating
	 * the actor's commands (i.e., making the Actor's decisions)
	 */
	protected Client<TimeBasedCommand> client;
	
	// some vital statistics
	protected int speed;
	protected int strength;
	protected int maxHealth;
	protected int currentHealth;
	
	protected String team;
	protected String name;
	
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
	
	public boolean isDisabled() {
		return currentHealth <= 0;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getStrength() {
		return strength;
	}

	public void setStrength(int strength) {
		this.strength = strength;
	}

	public int getMaxHealth() {
		return maxHealth;
	}

	public void setMaxHealth(int maxHealth) {
		this.maxHealth = maxHealth;
	}

	public int getCurrentHealth() {
		return currentHealth;
	}

	public void setCurrentHealth(int currentHealth) {
		this.currentHealth = currentHealth;
	}

	public void setClient(Client<TimeBasedCommand> client) {
		this.client = client;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public void setTeam(String team) {
		this.team = team;
	}
}
