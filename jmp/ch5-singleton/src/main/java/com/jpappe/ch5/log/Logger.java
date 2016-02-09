package com.jpappe.ch5.log;


/**
 * A simple logger implementation. The log level indicates the
 * minimum level that a message will actually be logged; the 
 * appender indicates where the log message is written to; the
 * formatter determines the format of the log message. 
 * 
 * 
 * @author Jacob Pappe
 *
 */
public final class Logger {

	private String context;
	
	public Logger(String context) {
		this.context = context;
	}
	
	public void append(LogLevel msgLevel, String msg) {
		LogEngine.getInstance().addLogEntry(
			new LogEntry.LogEntryBuilder(msg).level(msgLevel).context(this.context).build()
		);
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
	

	public String getContext() {
		return context;
	}

}
