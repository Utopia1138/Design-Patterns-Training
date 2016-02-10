package com.jpappe.ch5.log.appender.file;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

import com.jpappe.ch5.log.appender.LogMessageAppender;
import com.jpappe.ch5.log.exception.LogShutdownException;
import com.jpappe.ch5.log.exception.LogStartupException;

public class FileBasedAppender implements LogMessageAppender {

	private Path logFile;
	private BufferedWriter fileWriter;
	
	public FileBasedAppender(Path logFile) {
		this.logFile = logFile;
		System.out.println("New FileBasedAppender: " + this.logFile.toAbsolutePath().toString() );
	}

	@Override
	public void append(String msg) {
		System.out.println("Appending");
		if(fileWriter == null) {
			throw new IllegalStateException("`append' called without `startup'");
		}
		
		try {
			fileWriter.write(msg);
			fileWriter.flush();
		} catch (IOException e) {
			throw new RuntimeException("Error writing to log file " + logFile.toString(), e);
		}
	}

	@Override
	public void startup() throws LogStartupException {
		try {
			fileWriter = Files.newBufferedWriter(logFile, StandardOpenOption.APPEND);
		} catch (IOException e) {
			throw new LogStartupException("Unable to startup FileBasedAppender", e);
		}
	}

	@Override
	public void shutdown() throws LogShutdownException {
		try {
			fileWriter.close();
		} catch (IOException e) {
			throw new LogShutdownException("Unable to close log file " + logFile.toString(), e);
		}
	}

}
