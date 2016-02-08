package com.jpappe.ch5.log.appender;

import java.nio.file.Path;

public class FileAppender {

	private volatile static FileAppender instance;
	
	private Path logFilePath;
	
	public static FileAppender getInstance() {
		if( instance == null) {
			synchronized(FileAppender.class) {
				instance = new FileAppender();
			}
		}
		return instance;
	}
	
	private FileAppender() {
		
	}
	
	public void writeToFile(String logLine) {
		//Files.write(FileSystems.getDefault().getPath(".", filePath), arg1, arg2)
	}

}
