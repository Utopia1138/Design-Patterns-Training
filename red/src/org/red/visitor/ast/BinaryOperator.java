package org.red.visitor.ast;

import org.red.visitor.ASTVisitor;
import org.red.visitor.interpreter.Context.Type;

public abstract class BinaryOperator implements Expression {
	private final String ident;
	private final Expression lhs;
	private final Expression rhs;
	
	public BinaryOperator( String ident, Expression lhs, Expression rhs ) {
		this.ident = ident;
		this.lhs = lhs;
		this.rhs = rhs;
	}

	@Override
	public Type typeOf() {
		return lhs.typeOf() == Type.INFERRED ? rhs.typeOf() : lhs.typeOf();
	}
	
	@Override
	public void accept( ASTVisitor visitor ) {
		visitor.visit(this);
	}
	
	public String ident() {
		return ident;
	}

	public Expression lhs() {
		return lhs;
	}

	public Expression rhs() {
		return rhs;
	}

}
