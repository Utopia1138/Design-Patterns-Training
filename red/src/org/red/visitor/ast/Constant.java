package org.red.visitor.ast;

import org.red.visitor.ASTVisitor;
import org.red.visitor.Context;
import org.red.visitor.Context.Type;
import org.red.visitor.Context.Value;

public class Constant implements Expression {

	private final Value value;
	
	public Constant( Value value ) {
		this.value = value;
	}
	
	@Override
	public Value execute( Context context ) {
		return value;
	}

	@Override
	public void accept( ASTVisitor visitor ) {
		visitor.visit( this );
	}

	@Override
	public Type typeOf() {
		return value.type();
	}
	
	public Value value() {
		return value;
	}

}
