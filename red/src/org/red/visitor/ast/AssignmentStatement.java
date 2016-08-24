package org.red.visitor.ast;

import org.red.visitor.ASTVisitor;

public class AssignmentStatement implements Statement {
	
	private final String ident;
	private final Expression expression;
	
	public AssignmentStatement( String ident, Expression expression ) {
		this.ident = ident;
		this.expression = expression;
	}

	@Override
	public void accept( ASTVisitor visitor ) {
		visitor.visit( this );
	}

	public String ident() {
		return ident;
	}

	public Expression assignmentExpr() {
		return expression;
	}
}
