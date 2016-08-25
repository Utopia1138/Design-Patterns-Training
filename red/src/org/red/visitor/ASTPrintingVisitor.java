package org.red.visitor;

import java.util.List;

import org.red.visitor.ast.AssignmentStatement;
import org.red.visitor.ast.BinaryOperator;
import org.red.visitor.ast.Condition;
import org.red.visitor.ast.ConditionalLoop;
import org.red.visitor.ast.Constant;
import org.red.visitor.ast.DeclarationStatement;
import org.red.visitor.ast.LookupExpression;
import org.red.visitor.ast.Sequence;
import org.red.visitor.ast.Statement;
import org.red.visitor.ast.Terminal;
import org.red.visitor.ast.UnaryOperator;

public class ASTPrintingVisitor implements ASTVisitor {
	
	private String indent = "";
	
	private void indent() {
		indent += "  ";
		System.out.println();
	}

	private void undent() {
		indent = indent.substring( 0, indent.length() - 2 );
	}

	@Override
	public void visit( DeclarationStatement decl ) {
		System.out.printf( "decl {%s<%s>}", decl.ident(), decl.type() );
	}

	@Override
	public void visit( AssignmentStatement assign ) {
		System.out.printf( "assign {%s} -> ", assign.ident() );
		
		assign.assignmentExpr().accept( this );
	}

	@Override
	public void visit( Constant c ) {
		System.out.printf( "const %s(%s)", c.value().type(), c.value().get() );
	}
	
	@Override
	public void visit( Terminal terminal ) {
		terminal.expr().accept( this );
	}

	@Override
	public void visit( UnaryOperator op ) {
		System.out.printf( "call {%s} -> ", op.ident() );
		op.input().accept(this);
	}
	
	@Override
	public void visit( BinaryOperator op ) {
		System.out.printf( "call {%s} -> (", op.ident() );
		
		op.lhs().accept(this);
		
		System.out.print( ", " );
		
		op.rhs().accept(this);

		System.out.print( ") " );
	}

	@Override
	public void visit( LookupExpression lu ) {
		System.out.printf( "var {%s<%s>}", lu.ident(), lu.typeOf() );
	}

	@Override
	public void visit( Sequence sequence ) {
		System.out.print( "sequence" );
		indent();
		
		List<Statement> s = sequence.statements();
		for( int i = 0; i < s.size() - 1; ++i ) {
			System.out.print(indent);
			s.get(i).accept(this);
			System.out.println();
		}
		
		System.out.print(indent);
		s.get(s.size()-1).accept(this);

		undent();
	}
	
	@Override
	public void visit( Condition cond ) {
		System.out.print( "if -> " );
		cond.condition().accept(this);

		indent();
		System.out.print(indent);
		
		cond.onTrue().accept( this );

		if ( cond.hasElse() ) {
			System.out.println();
			System.out.print(indent);
			cond.onFalse().accept(this);
		}
		
		undent();
	}

	@Override
	public void visit( ConditionalLoop loop ) {
		System.out.print( "while -> " );
		loop.condition().accept(this);
		
		indent();
		System.out.print(indent);
		
		loop.body().accept( this );

		undent();
	}
}
