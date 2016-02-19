package org.axp.adaptor;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeMap;
import java.util.Map.Entry;

import org.axp.adaptor.sqlset.LookupTableDAO;
import org.axp.adaptor.sqlset.LookupTableMapAdaptor;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

/**
 * Test just our adaptor code, mocking the DAO itself.
 */
public class TestLookupTableMapAdaptor {
	private LookupTableMapAdaptor ssa;
	private LookupTableDAO dao;
	
	@Before
	public void setup() {
		this.dao = Mockito.mock( LookupTableDAO.class );
		this.ssa = new LookupTableMapAdaptor( this.dao );
	}

	@Test
	public void testSize() throws SQLException {
		Mockito.when( this.dao.getCount() ).thenReturn( 7 );
		assertEquals( "size", 7, this.ssa.size() );
	}
	
	@Test (expected = RuntimeException.class)
	public void testSizeException() throws SQLException {
		Mockito.when( this.dao.getCount() ).thenThrow( new SQLException() );
		this.ssa.size();
	}

	@Test
	public void testIsEmptyTrue() throws SQLException {
		Mockito.when( this.dao.getCount() ).thenReturn( 0 );
		assertTrue( "empty true", this.ssa.isEmpty() );
	}

	@Test
	public void testIsEmptyFalse() throws SQLException {
		Mockito.when( this.dao.getCount() ).thenReturn( 7 );
		assertFalse( "empty false", this.ssa.isEmpty() );
	}
	
	@Test (expected = RuntimeException.class)
	public void testIsEmptyException() throws SQLException {
		Mockito.when( this.dao.getCount() ).thenThrow( new SQLException() );
		this.ssa.isEmpty();
	}

	@Test
	public void testContainsValueTrue() throws SQLException {
		Mockito.when( this.dao.contains( "rabbit" ) ).thenReturn( true );
		assertTrue( "contains value true", this.ssa.containsValue( "rabbit" ) );
	}

	@Test
	public void testContainsValueFalse() throws SQLException {
		Mockito.when( this.dao.contains( "rabbit" ) ).thenReturn( false );
		assertFalse( "contains value false", this.ssa.containsValue( "rabbit" ) );
	}

	@Test
	public void testContainsValueNotString() {
		assertFalse( "contains value not a string", this.ssa.containsValue( true ) );
		Mockito.verifyZeroInteractions( this.dao );
	}
	
	@Test (expected = RuntimeException.class)
	public void testContainsValueException() throws SQLException {
		Mockito.when( this.dao.contains( Mockito.anyString() ) ).thenThrow( new SQLException() );
		this.ssa.containsValue( "rabbit" );
	}

	@Test
	public void testContainsKeyTrue() throws SQLException {
		Mockito.when( this.dao.containsKey( 42 ) ).thenReturn( true );
		assertTrue( "contains key true", this.ssa.containsKey( 42 ) );
	}

	@Test
	public void testContainsKeyFalse() throws SQLException {
		Mockito.when( this.dao.containsKey( 42 ) ).thenReturn( false );
		assertFalse( "contains key true", this.ssa.containsKey( 42 ) );
	}

	@Test
	public void testContainsKeyNotInteger() {
		assertFalse( "contains key not an int", this.ssa.containsKey( true ) );
		Mockito.verifyZeroInteractions( this.dao );
	}
	
	@Test (expected = RuntimeException.class)
	public void testContainsKeyException() throws SQLException {
		Mockito.when( this.dao.containsKey( Mockito.anyInt() ) ).thenThrow( new SQLException() );
		this.ssa.containsKey( 42 );
	}

	@Test
	public void testGetMatch() throws SQLException {
		Mockito.when( this.dao.lookup( 42 ) ).thenReturn( "rabbit" );
		assertEquals( "get match", "rabbit", this.ssa.get( 42 ) );
	}

	@Test
	public void testGetNoMatch() throws SQLException {
		Mockito.when( this.dao.lookup( 42 ) ).thenReturn( null );
		assertNull( "get no match", this.ssa.get( 42 ) );
	}

	@Test
	public void testGetNotInteger() {
		assertNull( "get not an int", this.ssa.get( true ) );
		Mockito.verifyZeroInteractions( this.dao );
	}
	
	@Test (expected = RuntimeException.class)
	public void testGetException() throws SQLException {
		Mockito.when( this.dao.lookup( Mockito.anyInt() ) ).thenThrow( new SQLException() );
		this.ssa.get( 42 );
	}

	@Test
	public void testPutReplace() throws SQLException {
		Mockito.when( this.dao.lookup( 42 ) ).thenReturn( "rabbit" );
		assertEquals( "put replacing existing", "rabbit", ssa.put( 42, "lizard" ) );
		Mockito.verify( this.dao ).setValue( 42, "lizard" );
	}

	@Test
	public void testPutNew() throws SQLException {
		Mockito.when( this.dao.lookup( 42 ) ).thenReturn( null );
		assertNull( "put new id", this.ssa.put( 42, "lizard" ) );
		Mockito.verify( this.dao ).setValue( 42, "lizard" );
	}
	
	@Test (expected = RuntimeException.class)
	public void testPutException() throws SQLException {
		Mockito.doThrow( new SQLException() ).when( this.dao ).setValue( Mockito.anyInt(), Mockito.anyString() );
		this.ssa.put( 42, "lizard" );
	}

	@Test
	public void testRemove() throws SQLException {
		Mockito.when( this.dao.lookup( 42 ) ).thenReturn( "rabbit" );
		assertEquals( "remove existing", "rabbit", this.ssa.remove( 42 ) );
		Mockito.verify( this.dao ).remove( 42 );
	}

	@Test
	public void testRemoveNoMatch() throws SQLException {
		Mockito.when( this.dao.lookup( 42 ) ).thenReturn( null );
		assertNull( "remove non-existing", this.ssa.remove( 42 ) );
		Mockito.verify( this.dao ).remove( 42 );
	}

	@Test
	public void testRemoveNotInteger() {
		assertNull( "remove not an int", this.ssa.remove( true ) );
		Mockito.verifyZeroInteractions( this.dao );
	}
	
	@Test (expected = RuntimeException.class)
	public void testRemoveException() throws SQLException {
		Mockito.doThrow( new SQLException() ).when( this.dao ).remove( Mockito.anyInt() );
		this.ssa.remove( 42 );
	}

	@Test
	public void testPutAll() throws SQLException {
		TreeMap<Integer, String> testData = new TreeMap<>();
		testData.put( 42, "rabbit" );
		
		this.ssa.putAll( testData );
		Mockito.verify( this.dao ).setValues( testData );
	}
	
	@Test (expected = RuntimeException.class)
	public void testPutAllException() throws SQLException {
		Mockito.doThrow( new SQLException() ).when( this.dao ).setValues( Mockito.any() );
		TreeMap<Integer, String> testData = new TreeMap<>();
		testData.put( 42, "rabbit" );
		
		this.ssa.putAll( testData );
	}

	@Test
	public void testClear() throws SQLException {
		this.ssa.clear();
		Mockito.verify( this.dao ).truncate();
	}
	
	@Test (expected = RuntimeException.class)
	public void testClearException() throws SQLException {
		Mockito.doThrow( new SQLException() ).when( this.dao ).truncate();
		this.ssa.clear();
	}

	@Test
	public void testKeySet() throws SQLException {
		Set<Integer> keys = new HashSet<>();
		Mockito.when( this.dao.getKeys() ).thenReturn( keys );
		assertEquals( "keyset", keys, this.ssa.keySet() );
	}
	
	@Test (expected = RuntimeException.class)
	public void testKeySetException() throws SQLException {
		Mockito.when( this.dao.getKeys() ).thenThrow( new SQLException() );
		this.ssa.keySet();
	}

	@Test
	public void testValues() throws SQLException {
		List<String> values = new ArrayList<>();
		Mockito.when( this.dao.getValues() ).thenReturn( values );
		assertEquals( "values", values, this.ssa.values() );
	}
	
	@Test (expected = RuntimeException.class)
	public void testValuesException() throws SQLException {
		Mockito.when( this.dao.getValues() ).thenThrow( new SQLException() );
		this.ssa.values();
	}

	@Test
	public void testEntrySet() throws SQLException {
		Set<Entry<Integer, String>> entries = new HashSet<>();
		Mockito.when( this.dao.getEntrySet() ).thenReturn( entries );
		assertEquals( "entry set", entries, this.ssa.entrySet() );
	}
	
	@Test (expected = RuntimeException.class)
	public void testEntrySetException() throws SQLException {
		Mockito.when( this.dao.getEntrySet() ).thenThrow( new SQLException() );
		this.ssa.entrySet();
	}
}
