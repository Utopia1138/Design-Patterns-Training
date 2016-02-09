package com.jpappe.ch5.log.exception;

@SuppressWarnings("serial")
public class LogStartupException extends Exception {


	public LogStartupException(String arg0) {
		super(arg0);
	}

	public LogStartupException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

}
