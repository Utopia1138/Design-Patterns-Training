package org.axp.proxy.dynamic.changelog;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Iterator;

import org.axp.proxy.dynamic.User;

/**
 * The concrete implementation of the system change log.
 */
public class SimpleChangeLog implements ChangeLog {
	private ArrayList<ChangeLogEntry> entries = new ArrayList<>();

	public Iterator<ChangeLogEntry> iterator() {
		return Collections.unmodifiableList( this.entries ).iterator();
	}

	public void addEntry( String action ) throws IllegalAccessException {
		// This method cannot be called directly, but only through the invocation handler
		throw new IllegalAccessException( "Can't add entry without user" );
	}
	
	public void addEntry( String action, User user ) {
		this.entries.add( new ChangeLogEntry( Calendar.getInstance().getTime(), user, action ) );
	}
	
	public void clear() {
		this.entries.clear();
	}
}
