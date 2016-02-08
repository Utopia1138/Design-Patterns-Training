package com.jpappe.ch5.log.layout;

import com.jpappe.ch5.log.Logger;

public interface LogMessageFormatter {

	String formatMessage(Logger logger, String msg);
	
}
