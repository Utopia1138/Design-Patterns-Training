package org.red.visitor.ast;

import org.red.visitor.ASTVisitor;

public class Terminal implements Statement {

	private Expression expression;
	public Terminal( Expression expression ) {
		this.expression = expression;
	}
	
	@Override
	public void accept( ASTVisitor visitor ) {
		visitor.visit( this );
	}

	public Expression expr() {
		return expression;
	}

}
