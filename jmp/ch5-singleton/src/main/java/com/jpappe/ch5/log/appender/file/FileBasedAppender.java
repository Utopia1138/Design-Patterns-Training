package com.jpappe.ch5.log.appender.file;

import java.nio.file.Path;

import com.jpappe.ch5.log.appender.LogMessageAppender;

public class FileBasedAppender implements LogMessageAppender {

	private Path logFile;
	
	public FileBasedAppender(Path logFile) {
		this.logFile = logFile;
	}

	@Override
	public void append(String msg) {
		// TODO Auto-generated method stub

	}

}
