package org.red.visitor;

import org.red.visitor.ast.AssignmentStatement;
import org.red.visitor.ast.BinaryOperator;
import org.red.visitor.ast.Condition;
import org.red.visitor.ast.Constant;
import org.red.visitor.ast.DeclarationStatement;
import org.red.visitor.ast.LookupExpression;
import org.red.visitor.ast.Sequence;
import org.red.visitor.ast.Terminal;
import org.red.visitor.ast.UnaryOperator;

public class ASTPrintingVisitor implements ASTVisitor {
	
	private String indent = "";
	private boolean nl = false;

	@Override
	public void visit( DeclarationStatement decl ) {
		System.out.printf( "[decl {%s<%s>}]%n", decl.ident(), decl.type() );
		System.out.print( indent );
		nl = true;
	}

	@Override
	public void visitPre( AssignmentStatement assign ) {
		System.out.printf( "[assign {%s}] -> ", assign.ident() );
	}
	
	@Override
	public void visitPost( AssignmentStatement assign ) {
		System.out.println();
		System.out.print( indent );
		nl = true;
	}

	@Override
	public void visit( Constant c ) {
		System.out.printf( "[const %s(%s)]", c.value().type(), c.value().get() );
	}
	
	@Override
	public void visit( Terminal terminal ) {
		if ( !nl ) {
			System.out.println();
			System.out.print( indent );
		}
		nl = false;
	}

	@Override
	public void visit( UnaryOperator op ) {
		System.out.printf( "[call {%s}] -> ", op.ident() );
	}
	
	@Override
	public void visitBefore( BinaryOperator op ) {
		System.out.printf( "[call {%s}] -> (", op.ident() );
	}
	
	@Override
	public void visitBetween( BinaryOperator op ) {
		System.out.print( ", " );
	}
	
	@Override
	public void visitAfter( BinaryOperator op ) {
		System.out.print( ") " );
	}

	@Override
	public void visit( LookupExpression lu ) {
		System.out.printf( "[var {%s}]", lu.ident() );
	}

	@Override
	public void visitPre( Sequence sequence ) {
		System.out.println( "[sequence]" );
		indent += "  ";
		System.out.print( indent );
	}
	
	@Override
	public void visitPost( Sequence sequence ) {
		indent = indent.substring( 0, indent.length() - 2 );
		System.out.println();
		System.out.print( indent );
	}

	@Override
	public void visitBefore( Condition cond ) {
		System.out.print( "[if] -> " );
	}
	
	@Override
	public void visitBeforeStatements( Condition cond ) {
		indent += "  ";
		System.out.println();
		System.out.print( indent );
	}

	@Override
	public void visitAfter( Condition cond ) {
		indent = indent.substring( 0, indent.length() - 2 );
		System.out.println();
		System.out.print( indent );
	}

}
