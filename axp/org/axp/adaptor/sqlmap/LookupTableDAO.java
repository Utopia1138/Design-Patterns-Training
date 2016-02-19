package org.axp.adaptor.sqlmap;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.AbstractMap.SimpleImmutableEntry;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 * This class is your bog-standard DAO, designed around a lookup table just with a key and value column
 */
public class LookupTableDAO {
	private Connection connection;
	private String tableName;
	private String keyCol;
	private String valueCol;
	
	public LookupTableDAO( Connection connection, String tableName, String keyCol, String valueCol ) {
		this.connection = connection;
		this.tableName = sanitise( tableName );
		this.keyCol = sanitise( keyCol );
		this.valueCol = sanitise( valueCol );
	}
	
	private String sanitise( String token ) {
		token = token.replace( "`", "" );
		return '`' + token + '`';
	}
	
	public int getCount() throws SQLException {
		PreparedStatement ps = this.connection.prepareStatement( "SELECT count(*) FROM " + this.tableName );
		
		try ( ResultSet rs = ps.executeQuery() ) {
			rs.next();
			return rs.getInt( 1 );
		}
	}

	public boolean contains( String value ) throws SQLException {
		PreparedStatement ps = this.connection.prepareStatement(
				"SELECT count(*) FROM " + this.tableName + " WHERE " + this.valueCol + " = ?" );
				
		ps.setString( 1, value );
				
		try ( ResultSet rs = ps.executeQuery() ) {
			rs.next();
			return ( rs.getInt( 1 ) != 0 );
		}
	}

	public boolean containsKey( int key ) throws SQLException {
		PreparedStatement ps = this.connection.prepareStatement(
			"SELECT count(*) FROM " + this.tableName + " WHERE " + this.keyCol + " = ?" );
				
		ps.setInt( 1, key );
				
		try ( ResultSet rs = ps.executeQuery() ) {
			rs.next();
			return ( rs.getInt( 1 ) != 0 );
		}
	}

	public String lookup( int key ) throws SQLException {
		String value = null;
		
		PreparedStatement ps = this.connection.prepareStatement(
				"SELECT " + this.valueCol + " FROM " + this.tableName + " WHERE " + this.keyCol + " = ?" );
		
		ps.setInt( 1, key );
		
		try ( ResultSet rs = ps.executeQuery() ) {
			if ( rs.next() ) {
				value = rs.getString( 1 );
			}
		}
		
		ps.close();
		
		return value;
	}

	public void setValue( int key, String value ) throws SQLException {
		PreparedStatement ps = this.connection.prepareStatement( 
			"INSERT INTO " + this.tableName + " ( " + this.keyCol + ", " + this.valueCol + " ) VALUES ( ?, ? )" );
						
		ps.setInt( 1, key );
		ps.setString( 2, value );
		ps.execute();
		ps.close();
	}

	public void setValues( Map<? extends Integer, ? extends String> m ) throws SQLException {
		if ( m.isEmpty() ) return;
		
		StringBuilder query = new StringBuilder( "INSERT INTO " ).append( this.tableName ).append( " ( " )
				.append( this.keyCol ).append( ", " ).append( this.valueCol ).append( " ) VALUES ( ?, ? )" );
		
		for ( int i = 1; i < m.size(); i++ ) {
			query.append( ", ( ?, ? )" );
		}
		
		PreparedStatement ps = this.connection.prepareStatement( query.toString() );
		int idx = 1;
		
		for ( int k : m.keySet() ) {
			ps.setInt( idx++, k );
			ps.setString( idx++, m.get( k ) );
		}
		
		ps.execute();
		ps.close();
	}

	public void remove( int key ) throws SQLException {		
		PreparedStatement ps = this.connection.prepareStatement(
			"DELETE FROM " + this.tableName + " WHERE " + this.keyCol + " = ?" );
		
		ps.setInt( 1, key );
		ps.execute();
		ps.close();
	}

	public void truncate() throws SQLException {
		this.connection.prepareStatement( "DELETE FROM " + this.tableName ).execute();
	}

	public Set<Integer> getKeys() throws SQLException {
		Set<Integer> keySet = new HashSet<>();
		PreparedStatement ps = this.connection.prepareStatement( "SELECT " + this.keyCol + " FROM " + this.tableName );
		
		try ( ResultSet rs = ps.executeQuery() ) {
			while ( rs.next() ) {
				keySet.add( rs.getInt( 1 ) );
			}
		}
		
		ps.close();
		return keySet;
	}

	public List<String> getValues() throws SQLException {
		List<String> values = new ArrayList<>();
		PreparedStatement ps = this.connection.prepareStatement( "SELECT " + this.valueCol + " FROM " + this.tableName );
		
		try ( ResultSet rs = ps.executeQuery() ) {
			while ( rs.next() ) {
				values.add( rs.getString( 1 ) );
			}
		}
		
		ps.close();
		return values;
	}

	public Set<Entry<Integer, String>> getEntrySet() throws SQLException {
		Set<Entry<Integer, String>> entrySet = new HashSet<>();
		
		PreparedStatement ps = this.connection.prepareStatement(
				"SELECT " + this.keyCol + ", " + this.valueCol + " FROM " + this.tableName );
		
		try ( ResultSet rs = ps.executeQuery() ) {
			while ( rs.next() ) {
				entrySet.add( new SimpleImmutableEntry<Integer,String>( rs.getInt( 1 ), rs.getString( 2 ) ) );
			}
		}
		
		ps.close();
		return entrySet;
	}
}
