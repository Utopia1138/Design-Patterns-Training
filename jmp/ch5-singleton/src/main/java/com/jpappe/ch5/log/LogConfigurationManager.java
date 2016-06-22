package com.jpappe.ch5.log;

import java.nio.file.Paths;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.jpappe.ch5.log.appender.file.FileBasedAppender;
import com.jpappe.ch5.log.exception.LogShutdownException;
import com.jpappe.ch5.log.exception.LogStartupException;
import com.jpappe.ch5.log.layout.DefaultLogMessageFormatter;



/**
 * Keeps track of all active log configurations
 * @author Jacob Pappe
 *
 */
public class LogConfigurationManager {

	private static LogConfigurationManager instance;
	
	public static LogConfigurationManager getInstance() {
		if ( instance == null ) {
			instance = new LogConfigurationManager();
		}
		return instance;
	}
	
	private Map<String,LogConfiguration> configurations;
	
	private LogConfigurationManager() {
		configurations = new ConcurrentHashMap<>();
		
		LogConfiguration rootLogger = new LogConfiguration();
		rootLogger.setAppender(new FileBasedAppender( Paths.get("rootLogger.log")));
		rootLogger.setLogLevel(LogLevel.INFO);
		rootLogger.setMessageFormatter(new DefaultLogMessageFormatter());
			
		configurations.put("root", rootLogger);
	}
	
	public void addLogConfiguration(String name, LogConfiguration config) {
		configurations.put(name, config);
	}
	
	public Map<String,LogConfiguration> getConfigurations() {
		return configurations;
	}

	public void startUp() {
		configurations.forEach( ( key, config ) -> {
			try {
			config.getAppender().startup();
			}
			catch ( LogStartupException e ) {
			System.err.println( "Error starting up appender for config " + key + ". Removing configuration." );
			e.printStackTrace( System.err );
			configurations.remove( key );
			}
		} );

	}

	public void shutDown() {
		configurations.forEach( ( key, config ) -> {

			try {
			config.getAppender().shutdown();
			}
			catch ( LogShutdownException e ) {
			System.err.println( "Error shutting down appender for config " + key );
			e.printStackTrace( System.err );
			}
		} );
	}

}
