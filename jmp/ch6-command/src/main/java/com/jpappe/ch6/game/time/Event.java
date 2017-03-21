package com.jpappe.ch6.game.time;

/**
 * Represents an event that occurs at a specific time
 * 
 * @author Jacob Pappe
 *
 */
public interface Event extends Comparable<Event> {

	void setTime(int time);
	
	int getTime();
	
}
