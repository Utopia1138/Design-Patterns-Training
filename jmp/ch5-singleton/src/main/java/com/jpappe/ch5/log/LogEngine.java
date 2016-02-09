package com.jpappe.ch5.log;

import java.util.concurrent.ConcurrentLinkedQueue;

public class LogEngine {

	private static LogEngine instance;
	
	public static LogEngine getInstance() {
		if ( instance == null ) {
			instance = new LogEngine();
		}
		return instance;
	}
	
	/**
	 * All log entries will go through this queue
	 */
	private ConcurrentLinkedQueue<LogEntry> logEntryQueue;
	
	/**
	 * When the engine is referenced for the first time,
	 * it starts everything up.
	 */
	private LogEngine() {
		logEntryQueue = new ConcurrentLinkedQueue<LogEntry>();
		/**
		 * start up a thread that polls on this queue 
		 */
	}
	
	public void addLogEntry(LogEntry entry) {
		logEntryQueue.add(entry);
	}

}
