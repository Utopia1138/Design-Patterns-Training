package org.red.visitor;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * As this is just showing off the visitor pattern and avoiding
 * other complexity, this uses a global rather than lexical
 * context.
 */
public class Context {
	public enum Type {
		INFERRED,
		INTEGER("int"),
		STRING("string"),
		FLOAT("float"),
		BOOLEAN("bool"),
		UNIT;
		
		private final String token;
		private Type() {
			this( null );
		}

		private Type( String token ) {
			this.token = token;
		}

		public boolean isDeclarable() {
			return token != null;
		}

		public static Optional<Type> fromToken( String token ) {
			Optional<Type> opt = Arrays.stream( Type.values() )
				.filter( Type::isDeclarable )
				.filter( type -> type.token.equals( token ) )
				.findFirst();
			
			return opt;
		}
	}

	public static class Value {
		public static final Value VOID = new Value( Type.UNIT );
		
		private final Type type;
		private String value;

		public Value( Type type ) {
			this.type = type;
		}

		public Value set( String value ) {
			this.value = value;
			return this;
		}

		public String get() {
			return value;
		}

		public Type type() {
			return type;
		}
	}

	private Map<String, Value> env = new HashMap<>();
	
	public Context push( String label, Value var ) {
		env.put( label, var );
		return this;
	}

	public Value value( String ident ) {
		return env.get( ident );
	}

	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		env.forEach( ( k, v ) -> str.append( String.format("%s: %s(%s)\n", k, v.type(), v.get()) ) );
		return str.deleteCharAt( str.length()-1 ).toString();
	}
	
}
