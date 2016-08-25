package org.red.visitor.ast;

import org.red.visitor.ASTVisitor;

public class ConditionalLoop implements Statement {
	private Expression condition;
	private Expression body;
	
	public ConditionalLoop( Expression condition, Expression body ) {
		this.condition = condition;
		this.body = body;
	}

	@Override
	public void accept( ASTVisitor visitor ) {
		visitor.visit( this );
	}

	public Expression condition() {
		return condition;
	}

	public Expression body() {
		return body;
	}

}
