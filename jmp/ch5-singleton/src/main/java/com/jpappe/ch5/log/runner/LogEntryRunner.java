package com.jpappe.ch5.log.runner;

import java.util.Map;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

import com.jpappe.ch5.log.LogConfiguration;
import com.jpappe.ch5.log.LogConfigurationManager;
import com.jpappe.ch5.log.LogEntry;

public class LogEntryRunner implements Runnable {

	private AtomicBoolean engineRunning;
	private LinkedBlockingQueue<LogEntry> logQueue;
	private Map<String, LogConfiguration> configurations;

	public LogEntryRunner(AtomicBoolean running,
			LinkedBlockingQueue<LogEntry> queue,
			Map<String, LogConfiguration> configs) {
		engineRunning = running;
		logQueue = queue;
		configurations = configs;
	}

	@Override
	public void run() {
		String myName = Thread.currentThread().getName();
		System.out.println("Starting LogEntry processing thread: " + myName);

		Map<String, LogConfiguration> configurations = LogConfigurationManager
				.getInstance().getConfigurations();

		while (engineRunning.get()) {
			System.out.println(myName + ": ...starting loop");

			try {
				final LogEntry entry = logQueue.poll(100,
						TimeUnit.MILLISECONDS);

				if (entry != null) {

					System.out.println(myName
							+ ": Pulled LogEntry from context "
							+ entry.getContext());

					/**
					 * go through all log configurations and find any that are
					 * compatible with the log entry. This means that the config
					 * name prefix-matches the log context and the log levels
					 * match
					 */
					configurations
							.forEach((name, config) -> {
								if (entry.getContext().startsWith(name)
										&& entry.getLogLevel().gteq(
												config.getLogLevel())) {

									System.out.println(myName
											+ ": Using LogConfiguration "
											+ name);

									try {
										config.getAppender().append(
												config.getMessageFormatter()
														.formatMessage(entry));
									} catch (Exception e) {
										System.err
												.println(myName
														+ ": Error encountered appending log entry: "
														+ e.getMessage());
									}
								}
							});
				}
			} catch (InterruptedException e) {
				System.err.println("Error encountered waiting for queue: "
						+ e.getMessage());
			}
		}

		System.out.println(myName + ": engine has turned off. Shutting down.");

	}
}
