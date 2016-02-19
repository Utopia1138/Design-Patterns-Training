package com.jpappe.ch5.log;

public enum LogLevel {
	TRACE(0),
	DEBUG(10),
	INFO(20),
	WARN(30),
	ERROR(40);
	
	private int level;
	private LogLevel(int level) {
		this.level = level;
	}
	
	public boolean gteq(LogLevel thatLevel) {
		return this.level >= thatLevel.level;
	}
};
