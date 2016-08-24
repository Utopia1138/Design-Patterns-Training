package org.red.visitor.ast;

import org.red.visitor.ASTVisitor;
import org.red.visitor.interpreter.Context.Type;

public class LookupExpression implements Expression {
	
	private String ident;
	private Type type;
	
	public LookupExpression( String ident, Type type ) {
		this.ident = ident;
		this.type = type;
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

	public void typeIs(Type type) {
		this.type = type;
	}
	
}
