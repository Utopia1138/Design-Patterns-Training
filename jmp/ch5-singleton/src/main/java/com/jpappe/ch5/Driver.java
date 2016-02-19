package com.jpappe.ch5;

import java.nio.file.Paths;

import com.jpappe.ch5.log.LogConfiguration;
import com.jpappe.ch5.log.LogConfigurationManager;
import com.jpappe.ch5.log.LogEngine;
import com.jpappe.ch5.log.LogLevel;
import com.jpappe.ch5.log.LogManager;
import com.jpappe.ch5.log.Logger;
import com.jpappe.ch5.log.appender.file.FileBasedAppender;
import com.jpappe.ch5.log.layout.DefaultLogMessageFormatter;

public class Driver {

	public static void main(String[] args) throws Exception {

		LogConfiguration config = new LogConfiguration();
		config.setAppender(new FileBasedAppender(Paths.get("main.log")));
		config.setMessageFormatter( new DefaultLogMessageFormatter() );
		config.setLogLevel(LogLevel.DEBUG);
		LogConfigurationManager.getInstance()
			.addLogConfiguration("main", config);
		
		LogEngine
			.getInstance()
			.setNumThreads(3)
			.startUp();
		
		Logger log = LogManager.getLogger("main-logger");
		
		log.trace("this is a trace message");
		log.debug("this is a debug message");
		log.info("this is an info message");
		log.warn("this is a warn message");
		log.error("This is an error message");
		
		LogEngine.getInstance().shutDown();

	}

}
