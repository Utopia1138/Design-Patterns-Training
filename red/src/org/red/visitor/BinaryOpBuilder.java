package org.red.visitor;

import java.util.function.BiFunction;

import org.red.visitor.Context.Type;
import org.red.visitor.Context.Value;
import org.red.visitor.Lexer.Token;
import org.red.visitor.ast.BinaryOperator;
import org.red.visitor.ast.Expression;

public class BinaryOpBuilder {
	public BinaryOperator binaryOperatorFor( Token op, Expression lhs, Expression rhs ) {
		Type operType = lhs.typeOf() == Type.INFERRED ? rhs.typeOf() : lhs.typeOf();
		
		if ( lhs.typeOf() != rhs.typeOf() && lhs.typeOf() != Type.INFERRED && rhs.typeOf() != Type.INFERRED ) {
			throw new RuntimeException( "Type mismatch in " + op.type() + ", lhs: " + lhs.typeOf() + ", rhs: " + rhs.typeOf() );
		}
		
		switch( op.type() ) {
		case OPERATOR_ADD: return add( operType, lhs, rhs ); 
		case OPERATOR_DIV: return div( operType, lhs, rhs );
		case OPERATOR_MULT: return mult( operType, lhs, rhs );
		case OPERATOR_SUB: return sub( operType, lhs, rhs );
		case OPERATOR_GT: return gt( operType, lhs, rhs );
		case OPERATOR_LT: return lt( operType, lhs, rhs );
		default:
			throw new RuntimeException( "No operator for token: " + op.type() );
		}
	}
	
	public BinaryOperator gt( Type type, Expression lhs, Expression rhs ) {
		switch( type ) {
		case FLOAT:
			return new BinaryOperator(">", floatBool( (a, b) -> a > b ), lhs, rhs, type);
		case INTEGER:
			return new BinaryOperator(">", intBool( (a, b) -> a > b ), lhs, rhs, type);
		}
		throw new RuntimeException( "> not supported for " + type );
	}

	public BinaryOperator lt( Type type, Expression lhs, Expression rhs ) {
		switch( type ) {
		case FLOAT:
			return new BinaryOperator("<", floatBool( (a, b) -> a < b ), lhs, rhs, type);
		case INTEGER:
			return new BinaryOperator("<", intBool( (a, b) -> a < b ), lhs, rhs, type);
		}
		throw new RuntimeException( "< not supported for " + type );
	}
	
	public BinaryOperator add( Type type, Expression lhs, Expression rhs ) {
		switch( type ) {
		case FLOAT:
			return new BinaryOperator("+", floatOp( (a, b) -> a + b ), lhs, rhs, type);
		case INTEGER:
			return new BinaryOperator("+", intOp( (a, b) -> a + b ), lhs, rhs, type);
		case STRING:
			return new BinaryOperator("+", (a, b) -> {
				return new Value( Type.STRING )
					.set( a.get() + b.get() );
			}, lhs, rhs, type );
		}
		throw new RuntimeException( "+ not supported for " + type );
	}
	
	public BinaryOperator div( Type type, Expression lhs, Expression rhs ) {
		switch( type ) {
		case FLOAT:
			return new BinaryOperator("/", floatOp( (a, b) -> a / b ), lhs, rhs, type);
		case INTEGER:
			return new BinaryOperator("/", intOp( (a, b) -> a / b ), lhs, rhs, type);
		}
		throw new RuntimeException( "/ not supported for " + type );
	}
	
	public BinaryOperator mult( Type type, Expression lhs, Expression rhs ) {
		switch( type ) {
		case FLOAT:
			return new BinaryOperator("*", floatOp( (a, b) -> a * b ), lhs, rhs, type);
		case INTEGER:
			return new BinaryOperator("*", intOp( (a, b) -> a * b ), lhs, rhs, type);
		}
		throw new RuntimeException( "* not supported for " + type );
	}
	
	public BinaryOperator sub( Type type, Expression lhs, Expression rhs ) {
		switch( type ) {
		case FLOAT:
			return new BinaryOperator("-", floatOp( (a, b) -> a - b ), lhs, rhs, type);
		case INTEGER:
			return new BinaryOperator("-", intOp( (a, b) -> a - b ), lhs, rhs, type);
		}
		throw new RuntimeException( "- not supported for " + type );
	}
	
	public BiFunction<Value, Value, Value> intOp( BiFunction<Integer, Integer, Integer> fun ) {
		return (a, b) -> {
			int ia = Integer.parseInt( a.get() );
			int ib = Integer.parseInt( b.get() );
			return new Value( Type.INTEGER )
				.set( fun.apply( ia, ib ).toString() );
		};
	}
	
	public BiFunction<Value, Value, Value> floatOp( BiFunction<Double, Double, Double> fun ) {
		return (a, b) -> {
			double ia = Double.parseDouble( a.get() );
			double ib = Double.parseDouble( b.get() );
			return new Value( Type.FLOAT )
				.set( fun.apply( ia, ib ).toString() );
		};
	}

	public BiFunction<Value, Value, Value> floatBool( BiFunction<Double, Double, Boolean> fun ) {
		return (a, b) -> {
			double ia = Double.parseDouble( a.get() );
			double ib = Double.parseDouble( b.get() );
			return new Value( Type.BOOLEAN )
				.set( fun.apply( ia, ib ).toString() );
		};
	}

	public BiFunction<Value, Value, Value> intBool( BiFunction<Integer, Integer, Boolean> fun ) {
		return (a, b) -> {
			int ia = Integer.parseInt( a.get() );
			int ib = Integer.parseInt( b.get() );
			
			return new Value( Type.BOOLEAN )
				.set( fun.apply( ia, ib ).toString() );
		};
	}
}
