package com.jpappe.ch5.log.appender.file;

import java.nio.file.Path;

public class LogFileWriter {

	private static LogFileWriter instance;
	public static LogFileWriter getInstance() {
		if ( instance == null ) {
			instance = new LogFileWriter();
		}
		
		return instance;
	}
	
	private LogFileWriter() {
		// do nothing
	}
	
	public int write( Path path, String message ) {
		// TODO
		return 0;
	}

}
