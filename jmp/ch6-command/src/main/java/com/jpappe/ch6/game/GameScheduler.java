package com.jpappe.ch6.game;

import java.util.PriorityQueue;

import com.jpappe.ch6.game.time.Event;

public class GameScheduler<T extends Event> {

	/**
	 * This is our event queue, which keeps track of
	 * all the future events in the game in "priority"
	 * order, where a command's priority corresponds to 
	 * when in the timeline of the game it occurs. A lower
	 * priority command will be executed sooner than a higher
	 * priority command
	 */
	private PriorityQueue<T> eventQueue;
	
	/**
	 * This keeps track of game time as it progresses
	 */
	private int currentTime;
	
	public GameScheduler() {
		eventQueue = new PriorityQueue<T>();
		currentTime = 0;
	}
	
	
	public int getCurrentTime() {
		return currentTime;
	}
	
	/**
	 * pull the next event off the event queue
	 * @return Command
	 */
	public T next() {
		T event = eventQueue.poll();
		currentTime = event.getTime();
		return event;
	}
	
	/**
	 * Add an event to the schedule
	 * @param event
	 */
	public void addEvent(T event) {
		eventQueue.add(event);
	}
	
	

}
