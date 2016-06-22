package com.jpappe.ch5.log;


public class LogEntry {

	private LogLevel logLevel;
	private String context;
	private String message;
	
	
	private LogEntry(LogEntryBuilder builder) {
		this.message = builder.message;
		this.context = builder.context;
		this.logLevel = builder.logLevel;
	}

	public static class LogEntryBuilder {
		private LogLevel logLevel;
		private String context;
		private String message;
		
		public LogEntryBuilder(String message) {
			this.message = message;
		}
		
		public LogEntryBuilder level(LogLevel level) {
			this.logLevel = level;
			return this;
		}
		
		public LogEntryBuilder context(String context) {
			this.context = context;
			return this;
		}
		
		public LogEntry build() {
			return new LogEntry(this);
		}
	}

	public LogLevel getLogLevel() {
		return logLevel;
	}

	public String getContext() {
		return context;
	}

	public String getMessage() {
		return message;
	}

}
