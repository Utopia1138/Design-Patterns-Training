package org.red.visitor.ast;

import org.red.visitor.ASTVisitor;
import org.red.visitor.Context;
import org.red.visitor.Context.Value;

public class Terminal implements Statement {

	private Expression expression;
	public Terminal( Expression expression ) {
		this.expression = expression;
	}
	
	@Override
	public Value execute( Context context ) {
		expression.execute( context );
		return Value.VOID;
	}

	@Override
	public void accept( ASTVisitor visitor ) {
		expression.accept( visitor );
		visitor.visit( this );
	}

}
