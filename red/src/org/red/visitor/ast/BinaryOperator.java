package org.red.visitor.ast;

import java.util.function.BiFunction;

import org.red.visitor.ASTVisitor;
import org.red.visitor.Context;
import org.red.visitor.Context.Type;
import org.red.visitor.Context.Value;

public class BinaryOperator implements Expression {
	private final String ident;
	private final BiFunction<Value, Value, Value> op;
	private final Expression lhs;
	private final Expression rhs;
	private final Type outType;
	
	public BinaryOperator( String ident, BiFunction<Value, Value, Value> op, Expression lhs, Expression rhs, Type outType ) {
		this.ident = ident;
		this.op = op;
		this.lhs = lhs;
		this.rhs = rhs;
		this.outType = outType;
	}
	
	@Override
	public Value execute( Context context ) {
		Value left = lhs.execute( context );
		Value right = rhs.execute( context );
		return op.apply( left, right );
	}

	@Override
	public Type typeOf() {
		return outType;
	}

	@Override
	public void accept( ASTVisitor visitor ) {
		visitor.visitBefore( this );
		lhs.accept( visitor );
		visitor.visitBetween( this );
		rhs.accept( visitor );
		visitor.visitAfter( this );
	}
	
	public String ident() {
		return ident;
	}
}
