package com.jpappe.ch6.command;

import com.jpappe.ch6.game.time.Event;

/**
 * A Command that is executed at a specific point in time.
 * 
 * @author Jacob Pappe
 *
 */
public abstract class TimeBasedCommand implements Command, Event {

	protected int time;

	public int compareTo(Event other) {
		return (this.time - other.getTime());
	}
	
	public int getTime() {
		return time;
	}
	
	public void setTime(int time) {
		this.time = time;
	}

}
