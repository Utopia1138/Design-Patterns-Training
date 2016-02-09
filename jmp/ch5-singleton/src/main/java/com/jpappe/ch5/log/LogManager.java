package com.jpappe.ch5.log;

/**
 * This class is responsible for instantiating individual Loggers
 * and maintaining a representation of the current logging configuration.
 * 
 * @author Jacob Pappe
 *
 */
public class LogManager {

	private static LogConfiguration config;
	
	public static void setLogConfiguration( LogConfiguration logConfiguration ) {
		config = logConfiguration;
	}
	
	public static Logger getLogger(String logContext) {
		return new Logger(logContext);
	}

}
