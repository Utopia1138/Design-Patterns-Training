package org.axp.proxy.dynamic.changelog;

import java.util.Date;

import org.axp.proxy.dynamic.User;

/**
 * A single entry on the system change log
 */
public class ChangeLogEntry {
	private final Date time;
	private final User user;
	private final String action;
	
	public ChangeLogEntry( Date time, User user, String action ) {
		this.time = time;
		this.user = user;
		this.action = action;
	}
	
	public Date getTime() {
		return (Date) this.time.clone();
	}
	
	public User getUser() {
		return this.user;
	}
	
	public String getAction() {
		return this.action;
	}
}
