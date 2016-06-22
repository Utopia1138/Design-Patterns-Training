package com.jpappe.ch5.log.layout;

import com.jpappe.ch5.log.LogEntry;

public interface LogMessageFormatter {

	String formatMessage( LogEntry entry );
	
}
