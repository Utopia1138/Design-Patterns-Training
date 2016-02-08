package com.jpappe.ch5.log;

import java.util.function.Supplier;

import com.jpappe.ch5.log.appender.FileAppender;
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

	private static LogConfiguration instance;
	
	private LogConfiguration() {}
	
	public static LogConfiguration getInstance() {
		if(instance == null) {
			instance = new LogConfiguration();
		}
		return instance;
	}
	
	private Supplier<LogMessageAppender> appenderSupplier;
	private Supplier<LogMessageFormatter> messageFormatterSupplier;
	private Supplier<LogLevel> logLevelSupplier;

	public Supplier<LogMessageAppender> getAppenderSupplier() {
		return appenderSupplier;
	}

	public void setAppenderSupplier(Supplier<LogMessageAppender> appenderSupplier) {
		this.appenderSupplier = appenderSupplier;
	}

	public Supplier<LogMessageFormatter> getMessageFormatterSupplier() {
		return messageFormatterSupplier;
	}

	public void setMessageFormatterSupplier(
			Supplier<LogMessageFormatter> messageFormatterSupplier) {
		this.messageFormatterSupplier = messageFormatterSupplier;
	}

	public Supplier<LogLevel> getLogLevelSupplier() {
		return logLevelSupplier;
	}

	public void setLogLevelSupplier(Supplier<LogLevel> logLevelSupplier) {
		this.logLevelSupplier = logLevelSupplier;
	}


}
