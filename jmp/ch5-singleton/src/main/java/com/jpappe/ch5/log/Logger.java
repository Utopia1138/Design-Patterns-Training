package com.jpappe.ch5.log;

import com.jpappe.ch5.log.appender.LogMessageAppender;
import com.jpappe.ch5.log.layout.LogMessageFormatter;

/**
 * A simple logger implementation. The log level indicates the
 * minimum level that a message will actually be logged; the 
 * appender indicates where the log message is written to; the
 * formatter determines the format of the log message. 
 * 
 * Once created, these objects are immutable.
 * 
 * @author Jacob Pappe
 *
 */
public class Logger {

	private String context;
	private LogLevel logLevel;
	private LogMessageAppender appender;
	private LogMessageFormatter formatter;
	
	private Logger(LoggerBuilder builder) {
		this.context = builder.context;
		this.logLevel = builder.logLevel;
		this.appender = builder.appender;
		this.formatter =  builder.formatter;
	}
	
	public void append(LogLevel msgLevel, String msg) {
		if( logLevel.gteq(msgLevel) ) {
			appender.append( formatter.formatMessage(this, msg) );
		}
	}
	
	public void debug(String msg) {
		this.append(LogLevel.DEBUG, msg);
	}
	
	public void trace(String msg) {
		this.append(LogLevel.TRACE, msg);
	}
	
	public void info(String msg) {
		this.append(LogLevel.INFO, msg);
	}
	
	public void warn(String msg) {
		this.append(LogLevel.WARN, msg);
	}
	
	public void error(String msg) {
		this.append(LogLevel.ERROR, msg);
	}
	
	/**
	 * A Builder that constructs a Logger
	 * @author Jacob Pappe
	 *
	 */
	public static class LoggerBuilder {
		public String context;
		private LogLevel logLevel;
		private LogMessageAppender appender;
		private LogMessageFormatter formatter;
		
		public LoggerBuilder(String context) {
			this.context = context;
		}
		
		public LoggerBuilder logLevel(LogLevel level) {
			logLevel = level;
			return this;
		}
		
		public LoggerBuilder logMessageAppender(LogMessageAppender appender) {
			this.appender = appender;
			return this;
		}
		
		public LoggerBuilder logMessageFormatter(LogMessageFormatter formatter) {
			this.formatter = formatter;
			return this;
		}
		
		public Logger build() {
			return new Logger(this);
		}
	}

	public String getContext() {
		return context;
	}

}
