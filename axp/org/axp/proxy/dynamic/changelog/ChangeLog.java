package org.axp.proxy.dynamic.changelog;

import java.util.Iterator;

import org.axp.proxy.dynamic.AccessCheck;
import org.axp.proxy.dynamic.AddUserParam;
import org.axp.proxy.dynamic.User;

/**
 * A system change log, with methods to read existing entries, add new ones, or clear.
 */
public interface ChangeLog {
	/**
	 * Iterate over existing entries in the log
	 * 
	 * @return a read-only iterator
	 * @throws IllegalAccessException if the user does not have LEVEL_READ access
	 */
	@AccessCheck(ChangeLogUser.LEVEL_READ)
	public Iterator<ChangeLogEntry> iterator() throws IllegalAccessException;
	
	/**
	 * Add a new item to the log, recording yourself as the user. (Under the hood, a call to this method will be redirected
	 * to {@link #addEntry(String, User)}.) 
	 * 
	 * @param action a short description of the action the user performed
	 * @throws IllegalAccessException if the user does not have LEVEL_APPEND access, or if the method was invoked directly
	 *   on the concrete implementation
	 */
	@AddUserParam
	@AccessCheck(ChangeLogUser.LEVEL_APPEND)
	public void addEntry( String action ) throws IllegalAccessException;
	
	/**
	 * Add a new item to the log, recording any user. This requires a higher privilege level than {@link #addEntry(String)}.
	 * 
	 * @param action a short description of the action the user performed
	 * @param user the user who performed the action
	 * @throws IllegalAccessException if the <em>calling</em> user does not have LEVEL_CHANGE access
	 */
	@AccessCheck(ChangeLogUser.LEVEL_CHANGE)
	public void addEntry( String action, User user ) throws IllegalAccessException;
	
	/**
	 * Clear the log
	 * 
	 * @throws IllegalAccessException if the user does not have LEVEL_CHANGE access
	 */
	@AccessCheck(ChangeLogUser.LEVEL_CHANGE)
	public void clear() throws IllegalAccessException;
}
