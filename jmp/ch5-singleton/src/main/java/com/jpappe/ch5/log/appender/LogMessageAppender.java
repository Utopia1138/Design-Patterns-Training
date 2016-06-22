package com.jpappe.ch5.log.appender;

import com.jpappe.ch5.log.exception.LogShutdownException;
import com.jpappe.ch5.log.exception.LogStartupException;

public interface LogMessageAppender {

	void startup() throws LogStartupException;
	void shutdown() throws LogShutdownException;
	
	void append(String msg);
	
}
