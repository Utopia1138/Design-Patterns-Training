package org.red.visitor.ast;

import java.util.function.Function;

import org.red.visitor.ASTVisitor;
import org.red.visitor.interpreter.Context.Type;
import org.red.visitor.interpreter.Context.Value;

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
	public Type typeOf() {
		return outType;
	}

	@Override
	public void accept( ASTVisitor visitor ) {
		visitor.visit( this );
	}
	
	public String ident() {
		return ident;
	}

	public Expression input() {
		return in;
	}

	public Function<Value, Value> op() {
		return op;
	}

}
