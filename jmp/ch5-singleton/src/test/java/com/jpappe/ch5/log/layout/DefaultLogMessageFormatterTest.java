package com.jpappe.ch5.log.layout;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.jpappe.ch5.log.LogEntry;
import com.jpappe.ch5.log.LogLevel;

public class DefaultLogMessageFormatterTest {

	private DefaultLogMessageFormatter formatter;

	@Before
	public void setup() throws Exception {
		formatter = new DefaultLogMessageFormatter();
	}

	@Test
	public void testFormatMessage() {
		LogEntry entry = new LogEntry.LogEntryBuilder( "test message" )
		      .context( "test-context" )
		      .level( LogLevel.TRACE )
		      .build();

		String raw = formatter.formatMessage( entry );
		String masked = raw.replaceAll( "\\d{4}\\-\\d{2}\\-\\d{2}T\\d{2}:\\d{2}:\\d{2}\\.\\d+", "YYYY-MM-DDTHH:MM:SS.MS" );
		assertEquals( "YYYY-MM-DDTHH:MM:SS.MS TRACE test-context: test message", masked );
	}

}
