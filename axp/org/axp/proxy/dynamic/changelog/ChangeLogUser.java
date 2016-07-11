package org.axp.proxy.dynamic.changelog;

import org.axp.proxy.dynamic.User;

/**
 * A user of the system change log
 */
public class ChangeLogUser extends User {
	public static final int LEVEL_NOACCESS = 0;
	public static final int LEVEL_READ = 1;
	public static final int LEVEL_APPEND = 2;
	public static final int LEVEL_CHANGE = 3;
	
	private final String userName;
	
	public ChangeLogUser( String userName, int privLevel ) {
		super( privLevel );
		this.userName = userName;
	}
	
	public String toString() {
		return this.userName;
	}
}
