package org.red.visitor.ast;

import java.util.function.Function;

import org.red.visitor.ASTVisitor;
import org.red.visitor.Context;
import org.red.visitor.Context.Type;
import org.red.visitor.Context.Value;

public class UnaryOperator implements Expression {
	
	private final String ident;
	private final Function<Value, Value> op;
	private final Expression in;
	private final Type outType;
	
	public UnaryOperator( String ident, Function<Value, Value> op, Expression in, Type outType ) {
		this.ident = ident;
		this.op = op;
		this.in = in;
		this.outType = outType;
	}
	
	@Override
	public Value execute( Context context ) {
		return op.apply( in.execute( context ) );
	}

	@Override
	public Type typeOf() {
		return outType;
	}

	@Override
	public void accept( ASTVisitor visitor ) {
		visitor.visit( this );
		in.accept( visitor );
	}
	
	public String ident() {
		return ident;
	}

}
