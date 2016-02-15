package com.jpappe.ch5.log.layout;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.jpappe.ch5.log.LogEntry;

/**
 * A pretty dumb message formatter that just prints the time
 * and the log context before the message itself
 * 
 * @author Jacob Pappe
 *
 */
public class DefaultLogMessageFormatter implements LogMessageFormatter {

	@Override
	public String formatMessage( LogEntry entry ) {

		return String.format( "%s %s %s: %s",
		      LocalDateTime.now().format( DateTimeFormatter.ISO_DATE_TIME ),
		      entry.getLogLevel().name(),
		      entry.getContext(),
		      entry.getMessage() );
	}

}
