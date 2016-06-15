package org.axp.proxy.dynamic.changelog;

import java.util.HashMap;
import java.util.Iterator;

import org.axp.proxy.dynamic.PrivilegeLevelsInvocationHandler;
import org.axp.proxy.dynamic.User;
import org.axp.proxy.dynamic.changelog.ChangeLog;
import org.axp.proxy.dynamic.changelog.ChangeLogEntry;
import org.axp.proxy.dynamic.changelog.SimpleChangeLog;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ChangeLogProxyTest {
	public User noelNoaccess = new ChangeLogUser( "noel", ChangeLogUser.LEVEL_NOACCESS );
	public User ritaReadaccess = new ChangeLogUser( "rita", ChangeLogUser.LEVEL_READ );
	public User aparnaAppendaccess = new ChangeLogUser( "aparna", ChangeLogUser.LEVEL_APPEND );
	public User chadChangeaccess = new ChangeLogUser( "chad", ChangeLogUser.LEVEL_CHANGE );
	
	private User[] allUsers = new User[] { noelNoaccess, ritaReadaccess, aparnaAppendaccess, chadChangeaccess };
	private HashMap<User, ChangeLog> accessPoints = new HashMap<>( 4 );
	
	@Before
	public void setup() {
		SimpleChangeLog actualChangeLog = new SimpleChangeLog();
		actualChangeLog.addEntry( "Reticulated splines", aparnaAppendaccess );
		
		for ( User user : allUsers ) {
			accessPoints.put( user, PrivilegeLevelsInvocationHandler.getProxy( actualChangeLog, user ) );
		}
	}
	
	@Test( expected = IllegalAccessException.class )
	public void testIterateNoaccess() throws IllegalAccessException {
		accessPoints.get( noelNoaccess ).iterator();
	}
	
	private void testIterateAccess( User user ) throws IllegalAccessException {
		Iterator<ChangeLogEntry> entries = accessPoints.get( user ).iterator();
		assertTrue( "No entries", entries.hasNext() );
		ChangeLogEntry e = entries.next();
		assertEquals( "User mismatch", aparnaAppendaccess, e.getUser() );
		assertEquals( "Status mismatch", "Reticulated splines", e.getAction() );
		assertNotNull( "Null time", e.getTime() );
		assertFalse( "More entries than expected", entries.hasNext() );
	}
	
	@Test
	public void testIterateReadaccess() throws IllegalAccessException {
		testIterateAccess( ritaReadaccess );
	}
	
	@Test
	public void testIterateAppendaccess() throws IllegalAccessException {
		testIterateAccess( aparnaAppendaccess );
	}
	
	@Test
	public void testIterateChangeaccess() throws IllegalAccessException {
		testIterateAccess( chadChangeaccess );
	}
	
	@Test( expected = UnsupportedOperationException.class )
	public void testIteratorIsReadOnly() throws IllegalAccessException {
		Iterator<ChangeLogEntry> entries = accessPoints.get( chadChangeaccess ).iterator();
		assertTrue( "No entries", entries.hasNext() );
		entries.next();
		entries.remove();
	}
	
	@Test( expected = IllegalAccessException.class )
	public void testAddEntry2Noaccess() throws IllegalAccessException {
		accessPoints.get( noelNoaccess ).addEntry( "Do more stuff", noelNoaccess );
	}
	
	@Test( expected = IllegalAccessException.class )
	public void testAddEntry2Readaccess() throws IllegalAccessException {
		accessPoints.get( ritaReadaccess ).addEntry( "Do more stuff", ritaReadaccess );
	}
	
	@Test( expected = IllegalAccessException.class )
	public void testAddEntry2Appendaccess() throws IllegalAccessException {
		accessPoints.get( aparnaAppendaccess ).addEntry( "Do more stuff", aparnaAppendaccess );
	}
	
	@Test
	public void testAddEntry2Changeaccess() throws IllegalAccessException {
		accessPoints.get( chadChangeaccess ).addEntry( "Do more stuff", chadChangeaccess );
		Iterator<ChangeLogEntry> entries = accessPoints.get( chadChangeaccess ).iterator();
		assertTrue( "No entries", entries.hasNext() );
		assertNotNull( "First entry", entries.next() );
		assertTrue( "Not enough entries", entries.hasNext() );
		ChangeLogEntry e = entries.next();
		assertEquals( "User mismatch", chadChangeaccess, e.getUser() );
		assertEquals( "Status mismatch", "Do more stuff", e.getAction() );
		assertNotNull( "Null time", e.getTime() );
		assertFalse( "More entries than expected", entries.hasNext() );
	}
	
	@Test( expected = IllegalAccessException.class )
	public void testAddEntry1Noaccess() throws IllegalAccessException {
		accessPoints.get( noelNoaccess ).addEntry( "Do more stuff", noelNoaccess );
	}
	
	@Test( expected = IllegalAccessException.class )
	public void testAddEntry1Readaccess() throws IllegalAccessException {
		accessPoints.get( ritaReadaccess ).addEntry( "Do more stuff", ritaReadaccess );
	}
	
	private void testAddEntry1Access( User user ) throws IllegalAccessException {
		accessPoints.get( user ).addEntry( "Do more stuff" );
		Iterator<ChangeLogEntry> entries = accessPoints.get( user ).iterator();
		assertTrue( "No entries", entries.hasNext() );
		assertNotNull( "First entry", entries.next() );
		assertTrue( "Not enough entries", entries.hasNext() );
		ChangeLogEntry e = entries.next();
		assertEquals( "User mismatch", user, e.getUser() );
		assertEquals( "Status mismatch", "Do more stuff", e.getAction() );
		assertNotNull( "Null time", e.getTime() );
		assertFalse( "More entries than expected", entries.hasNext() );
	}
	
	@Test
	public void testAddEntry1Appendaccess() throws IllegalAccessException {
		testAddEntry1Access( aparnaAppendaccess );
	}
	
	@Test
	public void testAddEntry1Changeaccess() throws IllegalAccessException {
		testAddEntry1Access( chadChangeaccess );
	}
	
	@Test( expected = IllegalAccessException.class )
	public void testClearNoaccess() throws IllegalAccessException {
		accessPoints.get( noelNoaccess ).clear();
	}
	
	@Test( expected = IllegalAccessException.class )
	public void testClearReadaccess() throws IllegalAccessException {
		accessPoints.get( ritaReadaccess ).clear();
	}
	
	@Test( expected = IllegalAccessException.class )
	public void testClearAppendaccess() throws IllegalAccessException {
		accessPoints.get( aparnaAppendaccess ).clear();
	}
	
	@Test
	public void testClearChangeaccess() throws IllegalAccessException {
		accessPoints.get( chadChangeaccess ).clear();
		Iterator<ChangeLogEntry> entries = accessPoints.get( chadChangeaccess ).iterator();
		assertFalse( "Expected no entries", entries.hasNext() );
	}
}
