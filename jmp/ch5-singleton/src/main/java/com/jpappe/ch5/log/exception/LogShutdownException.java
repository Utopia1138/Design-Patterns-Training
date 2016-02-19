package com.jpappe.ch5.log.exception;

@SuppressWarnings("serial")
public class LogShutdownException extends Exception {


	public LogShutdownException(String message) {
		super(message);
	}

	public LogShutdownException(String message, Throwable cause) {
		super(message, cause);
	}

}
