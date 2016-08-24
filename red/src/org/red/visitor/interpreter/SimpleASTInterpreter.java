package org.red.visitor.interpreter;

import org.red.visitor.ASTVisitor;
import org.red.visitor.ast.AssignmentStatement;
import org.red.visitor.ast.BinaryOperator;
import org.red.visitor.ast.Condition;
import org.red.visitor.ast.ConditionalLoop;
import org.red.visitor.ast.Constant;
import org.red.visitor.ast.DeclarationStatement;
import org.red.visitor.ast.LookupExpression;
import org.red.visitor.ast.Sequence;
import org.red.visitor.ast.Terminal;
import org.red.visitor.ast.UnaryOperator;
import org.red.visitor.ast.binop.AdditionOperator;
import org.red.visitor.ast.binop.DivisionOperator;
import org.red.visitor.ast.binop.GreaterThanOperator;
import org.red.visitor.ast.binop.LessThanOperator;
import org.red.visitor.ast.binop.MultiplicationOperator;
import org.red.visitor.ast.binop.SubtractionOperator;
import org.red.visitor.interpreter.Context.Type;
import org.red.visitor.interpreter.Context.Value;

public class SimpleASTInterpreter implements ASTVisitor {
	
	private Context context = new Context();
	
	public Context context() {
		return context;
	}

	@Override
	public void visit(DeclarationStatement decl) {
		context.push( decl.ident(), new Value( decl.type() ) );
		context.last(Value.VOID); 
	}

	@Override
	public void visit(Constant constant) {
		context.last(constant.value());
	}

	@Override
	public void visit(UnaryOperator op) {
		op.input().accept(this);
		context.last( op.op().apply(context.last()) );
	}

	@Override
	public void visit(LookupExpression lu) {
		context.last(context.value(lu.ident()));
	}

	@Override
	public void visit(AssignmentStatement assign) {
		assign.assignmentExpr().accept(this);
		context.value( assign.ident() ).set( context.last().get() );
		context.last( Value.VOID );
	}

	@Override
	public void visit(Terminal terminal) {
		terminal.expr().accept(this);
		context.last( Value.VOID );
	}

	@Override
	public void visit(Sequence sequence) {
		sequence.forEach( s -> s.accept(this) );
		context.last( Value.VOID );
	}

	@Override
	public void visit(Condition c) {
		c.condition().accept(this);
		Value check = context.last();
		
		if ( check.get().equals( "true" ) ) {
			c.onTrue().accept(this);
		}
		else if ( c.hasElse() ) {
			c.onFalse().accept(this);
		}
		
		context.last(Value.VOID);
	}

	@Override
	public void visit(ConditionalLoop loop) {
		while( true ) {
			loop.condition().accept(this);
			
			if ( !context.last().get().equals("true") ) {
				break;
			}

			loop.body().accept(this);
		}
		
		context.last(Value.VOID);
	}
	
	
	@Override
	public void visit( AdditionOperator add ) {
		runBinaryBuiltinOp( add, (lhs, rhs) -> {
			switch(add.typeOf()) {
			case FLOAT: return new Value( Type.FLOAT ).set( lhs.asFloat() + rhs.asFloat() );
			case INTEGER: return new Value( Type.INTEGER ).set( lhs.asInt() + rhs.asInt() );
			case STRING: return new Value( Type.STRING ).set(lhs.get() + rhs.get());
			}
			return null;
		});
	}
	
	@Override
	public void visit( SubtractionOperator sub ) {
		runBinaryBuiltinOp( sub, (lhs, rhs) -> {
			switch(sub.typeOf()) {
			case FLOAT: return new Value( Type.FLOAT ).set( lhs.asFloat() - rhs.asFloat() );
			case INTEGER: return new Value( Type.INTEGER ).set( lhs.asInt() - rhs.asInt() );
			}
			return null;
		});
	}

	@Override
	public void visit( DivisionOperator div ) {
		runBinaryBuiltinOp( div, (lhs, rhs) -> {
			switch(div.typeOf()) {
			case FLOAT: return new Value( Type.FLOAT ).set( lhs.asFloat() / rhs.asFloat() );
			case INTEGER: return new Value( Type.INTEGER ).set( lhs.asInt() / rhs.asInt() );
			}
			return null;
		});
	}

	@Override
	public void visit( MultiplicationOperator multi ) {
		runBinaryBuiltinOp( multi, (lhs, rhs) -> {
			switch(multi.typeOf()) {
			case FLOAT: return new Value( Type.FLOAT ).set( lhs.asFloat() * rhs.asFloat() );
			case INTEGER: return new Value( Type.INTEGER ).set( lhs.asInt() * rhs.asInt() );
			}
			return null;
		});
	}

	@Override
	public void visit( LessThanOperator lt ) {
		runBinaryBuiltinOp( lt, (lhs, rhs) -> {
			switch(lt.typeOf()) {
			case FLOAT: return new Value( Type.BOOLEAN ).set( lhs.asFloat() < rhs.asFloat() );
			case INTEGER: return new Value( Type.BOOLEAN ).set( lhs.asInt() < rhs.asInt() );
			}
			return null;
		});
	}

	@Override
	public void visit( GreaterThanOperator gt ) {
		runBinaryBuiltinOp( gt, (lhs, rhs) -> {
			switch(gt.typeOf()) {
			case FLOAT: return new Value( Type.BOOLEAN ).set( lhs.asFloat() > rhs.asFloat() );
			case INTEGER: return new Value( Type.BOOLEAN ).set( lhs.asInt() > rhs.asInt() );
			}
			return null;
		});
	}

	public void runBinaryBuiltinOp( BinaryOperator op, java.util.function.BinaryOperator<Value> runner ) {
		op.lhs().accept(this);
		Value lhs = context.last();
		
		op.rhs().accept(this);
		Value rhs = context.last();

		context.last( runner.apply(lhs, rhs) );
	}

	@Override
	public void visit(BinaryOperator binop) { /* Await the underlying type */ }

}
