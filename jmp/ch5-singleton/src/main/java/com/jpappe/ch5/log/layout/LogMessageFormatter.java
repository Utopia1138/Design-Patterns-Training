package com.jpappe.ch5.log.layout;


public interface LogMessageFormatter {

	String formatMessage(String context, String msg);
	
}
