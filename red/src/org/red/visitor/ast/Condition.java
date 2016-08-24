package org.red.visitor.ast;

import org.red.visitor.ASTVisitor;
import org.red.visitor.Context;
import org.red.visitor.Context.Value;

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
	public Value execute( Context context ) {
		Value cond = condition.execute( context );
		
		if ( cond.get().equals( "true" ) ) {
			onTrue.execute( context );
		}
		else if ( onFalse != null ) {
			onFalse.execute( context );
		}
		
		return Value.VOID;
	}

	@Override
	public void accept( ASTVisitor visitor ) {
		visitor.visitBefore( this );
		condition.accept( visitor );
		visitor.visitBeforeStatements( this );
		onTrue.accept( visitor );
		if ( onFalse != null )
			onFalse.accept( visitor );
		visitor.visitAfter( this );
	}

}
