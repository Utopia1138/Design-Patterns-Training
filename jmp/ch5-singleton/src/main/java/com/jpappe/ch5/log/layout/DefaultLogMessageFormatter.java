package com.jpappe.ch5.log.layout;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.jpappe.ch5.log.Logger;

/**
 * A pretty dumb message formatter that just prints the time
 * and the log context before the message itself
 * 
 * @author Jacob Pappe
 *
 */
public class DefaultLogMessageFormatter implements LogMessageFormatter {

	@Override
	public String formatMessage(String context, String msg) {
		System.out.println("formatMessage()");
		return String.format("%s\t%s: %s", 
				LocalDate.now().format( DateTimeFormatter.ISO_DATE_TIME ),
				context,
				msg );
	}

}
