package com.jpappe.ch5.log;

import java.util.function.Supplier;

import com.jpappe.ch5.log.appender.LogMessageAppender;
import com.jpappe.ch5.log.layout.LogMessageFormatter;

/**
 * This object dictates configuration of our logger. It's basically
 * an Abstract Factory, each of whose methods pass a Lambda for generating
 * an object of the appropriate type.
 * 
 * @author Jacob Pappe
 *
 */
public class LogConfiguration {
	
	private LogMessageAppender appender;
	private LogMessageFormatter messageFormatter;
	private LogLevel logLevel;
	public LogMessageAppender getAppender() {
		return appender;
	}
	public void setAppender(LogMessageAppender appender) {
		this.appender = appender;
	}
	public LogMessageFormatter getMessageFormatter() {
		return messageFormatter;
	}
	public void setMessageFormatter(LogMessageFormatter messageFormatter) {
		this.messageFormatter = messageFormatter;
	}
	public LogLevel getLogLevel() {
		return logLevel;
	}
	public void setLogLevel(LogLevel logLevel) {
		this.logLevel = logLevel;
	}
}
