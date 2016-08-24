package org.red.visitor.ast;

import org.red.visitor.ASTVisitor;
import org.red.visitor.Context;
import org.red.visitor.Context.Value;

public class AssignmentStatement implements Statement {
	
	private final String ident;
	private final Expression expression;
	
	public AssignmentStatement( String ident, Expression expression ) {
		this.ident = ident;
		this.expression = expression;
	}

	@Override
	public Value execute( Context context ) {
		context.value( ident )
			.set( expression.execute( context ).get() );

		return Value.VOID;
	}

	@Override
	public void accept( ASTVisitor visitor ) {
		visitor.visitPre( this );
		expression.accept( visitor );
		visitor.visitPost( this );
	}

	public String ident() {
		return ident;
	}

	public Expression assignmentExpr() {
		return expression;
	}
}
