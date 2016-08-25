package org.red.visitor.ast;

import org.red.visitor.ASTVisitor;

public class Condition implements Statement {
	
	private Expression condition;
	private Expression onTrue;
	private Expression onFalse;
	
	public Condition( Expression condition, Expression onTrue, Expression onFalse ) {
		this.condition = condition;
		this.onTrue = onTrue;
		this.onFalse = onFalse;
	}

	@Override
	public void accept( ASTVisitor visitor ) {
		visitor.visit(this);
	}

	public Expression condition() {
		return condition;
	}

	public Expression onTrue() {
		return onTrue;
	}

	public Expression onFalse() {
		return onFalse;
	}

	public boolean hasElse() {
		return onFalse != null;
	}

}
