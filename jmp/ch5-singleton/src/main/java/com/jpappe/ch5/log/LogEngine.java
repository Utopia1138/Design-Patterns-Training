package com.jpappe.ch5.log;

import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

import com.jpappe.ch5.log.exception.LogShutdownException;

public class LogEngine {

	private static LogEngine instance;

	public static LogEngine getInstance() {
		if (instance == null) {
			instance = new LogEngine();
		}
		return instance;
	}

	/**
	 * All log entries will go through this queue
	 */
	private final LinkedBlockingQueue<LogEntry> logEntryQueue;
	/**
	 * This is how we'll signal to our processor threads
	 */
	private final AtomicBoolean engineRunning;
	/**
	 * This manages our threads
	 */
	private final ExecutorService executor;

	/**
	 * When the engine is referenced for the first time, it starts everything
	 * up.
	 */
	private LogEngine() {
		logEntryQueue = new LinkedBlockingQueue<LogEntry>();
		engineRunning = new AtomicBoolean(false);
		executor = Executors.newFixedThreadPool(5);
	}

	public void startUp() {

		logEntryQueue.add(new LogEntry.LogEntryBuilder("LogEngine starting up")
				.context("root").level(LogLevel.INFO).build());

		engineRunning.set(true);

		executor.submit(() -> {

			System.out.println("Starting LogEntry processing thread: "
					+ Thread.currentThread().getName());

			Map<String, LogConfiguration> configurations = LogConfigurationManager
					.getInstance().getConfigurations();
			while (engineRunning.get()) {
				System.out.println("...starting loop");
				try {
					final LogEntry entry = logEntryQueue.take();
					System.out.println("Pulled LogEntry from context "
							+ entry.getContext());

					/**
					 * go through all log configurations and find any that are
					 * compatible with the log entry. This means that the config
					 * name prefix-matches the log context and the log levels
					 * match
					 */
					configurations.forEach((name, config) -> {
						if (entry.getContext().startsWith(name)
								&& entry.getLogLevel().gteq(
										config.getLogLevel())) {

							System.out
									.println("Using LogConfiguration " + name);

							config.getAppender().append(
									config.getMessageFormatter().formatMessage(
											entry.getContext(),
											entry.getMessage()));
						}
					});
				} catch (InterruptedException e) {
					System.err.println("Error encountered waiting for queue: "
							+ e.getMessage());
				}
				System.out.println("...reached end of loop");
			}
		});
	}

	public void shutDown() throws LogShutdownException {
		System.out.println("Shutting down LogEngine");
		// wait for all messages to get off the queue
		while (logEntryQueue.peek() != null) {
			try {
				System.out.println("Log Queue is not empty. Waiting 100ms.");
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				throw new LogShutdownException(
						"Error waiting until log entry queue is empty", e);
			}
		}
		engineRunning.set(false);

		// now shutdown the executor
		executor.shutdown();
		try {
			executor.awaitTermination(5, TimeUnit.SECONDS);
		} catch (InterruptedException e) {
			System.err.println("Tasks interrupted shutting down executor: "
					+ e.getMessage());
		} finally {
			if (!executor.isTerminated()) {
				System.err.println("cancel non-finished tasks");
			}
			executor.shutdownNow();
			System.out.println("shutdown finished");
		}
	}

	public void addLogEntry(LogEntry entry) {
		System.out.println("Adding log entry from context "
				+ entry.getContext());
		logEntryQueue.add(entry);
	}

}
