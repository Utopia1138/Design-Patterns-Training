package org.axp.adaptor.sqlmap;

import java.sql.SQLException;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

/**
 * This class is an Adapter to the Data Access Object, adapting it to the Map interface.
 * 
 * This means we can pass the adapted DAO into any method that needs a Map, and all transformations
 * will be automatically backed by our database.
 * 
 * This may or may not be a wise thing to do. ;)
 */
public class LookupTableMapAdaptor implements Map<Integer, String> {
	private LookupTableDAO dao;
	
	public LookupTableMapAdaptor( LookupTableDAO dao ) {
		this.dao = dao;
	}
	
	@Override
	public int size() {
		try {
			return dao.getCount();
		}
		catch ( SQLException e ) {
			throw new RuntimeException( e );
		}
	}

	@Override
	public boolean isEmpty() {
		return ( size() == 0 );
	}

	@Override
	public boolean containsValue( Object o ) {
		if ( o instanceof String ) {
			try {
				return this.dao.contains( (String) o );
			}
			catch ( SQLException e ) {
				throw new RuntimeException( e );
			}
		}
		
		return false;
	}

	@Override
	public boolean containsKey( Object o ) {
		if ( o instanceof Integer ) {
			try {
				return this.dao.containsKey( (Integer) o );
			}
			catch ( SQLException e ) {
				throw new RuntimeException( e );
			}
		}
		
		return false;
	}

	@Override
	public String get( Object key ) {
		if ( key instanceof Integer ) {
			try {
				return this.dao.lookup( (Integer) key );
			}
			catch ( SQLException e ) {
				throw new RuntimeException( e );
			}
		}
		
		return null;
	}

	@Override
	public String put( Integer key, String value ) {
		String old = get( key );
		
		try {
			this.dao.setValue( key, value );
		}
		catch ( SQLException e ) {
			throw new RuntimeException( e );
		}
		
		return old;
	}

	@Override
	public String remove( Object key ) {
		String old = get( key );
		
		if ( key instanceof Integer ) {
			try {
				this.dao.remove( (Integer) key );
			}
			catch ( SQLException e ) {
				throw new RuntimeException( e );
			}
		}
		
		return old;
	}

	@Override
	public void putAll( Map<? extends Integer, ? extends String> m ) {
		try {
			this.dao.setValues( m );
		}
		catch ( SQLException e ) {
			throw new RuntimeException( e );
		}
	}

	@Override
	public void clear() {
		try {
			this.dao.truncate();
		}
		catch ( SQLException e ) {
			throw new RuntimeException( e );
		}
	}

	@Override
	public Set<Integer> keySet() {
		try {
			return this.dao.getKeys();
		}
		catch ( SQLException e ) {
			throw new RuntimeException( e );
		}
	}

	@Override
	public Collection<String> values() {
		try {
			return this.dao.getValues();
		}
		catch ( SQLException e ) {
			throw new RuntimeException( e );
		}
	}

	@Override
	public Set<Entry<Integer, String>> entrySet() {
		try {
			return this.dao.getEntrySet();
		}
		catch ( SQLException e ) {
			throw new RuntimeException( e );
		}
	}
}
