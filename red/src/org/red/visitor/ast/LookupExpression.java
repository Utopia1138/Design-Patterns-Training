package org.red.visitor.ast;

import org.red.visitor.ASTVisitor;
import org.red.visitor.Context;
import org.red.visitor.Context.Type;
import org.red.visitor.Context.Value;

public class LookupExpression implements Expression {
	
	private String ident;
	private Type type;
	
	public LookupExpression( String ident, Type type ) {
		this.ident = ident;
		this.type = type;
	}

	@Override
	public Value execute( Context context ) {
		return context.value( ident );
	}

	@Override
	public Type typeOf() {
		return type;
	}

	@Override
	public void accept( ASTVisitor visitor ) {
		visitor.visit( this );
	}

	public String ident() {
		return ident;
	}
	
}
