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
import org.red.visitor.interpreter.Context.Type;

public class PrettyPrinter implements ASTVisitor {
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
		System.out.printf( "%s : %s", decl.ident(), decl.type().token() );
	}

	@Override
	public void visit( AssignmentStatement assign ) {
		System.out.printf( "%s = ", assign.ident() );
		
		assign.assignmentExpr().accept( this );
	}

	@Override
	public void visit( Constant c ) {
		if ( c.value().type() == Type.STRING ) {
			System.out.printf( "\"%s\"", c.value().get() );
		} else {
			System.out.printf( "%s", c.value().get() );
		}
	}
	
	@Override
	public void visit( Terminal terminal ) {
		terminal.expr().accept( this );
	}

	@Override
	public void visit( UnaryOperator op ) {
		System.out.printf( "%s ", op.ident() );
		op.input().accept(this);
	}
	
	@Override
	public void visit( BinaryOperator op ) {
		op.lhs().accept(this);
		
		System.out.printf( " %s ", op.ident() );
		
		op.rhs().accept(this);
	}

	@Override
	public void visit( LookupExpression lu ) {
		System.out.printf( "%s", lu.ident() );
	}

	@Override
	public void visit( Sequence sequence ) {
		List<Statement> s = sequence.statements();
		for( int i = 0; i < s.size() - 1; ++i ) {
			System.out.print(indent);
			s.get(i).accept(this);
			System.out.println();
		}
		
		System.out.print(indent);
		s.get(s.size()-1).accept(this);
	}
	
	@Override
	public void visit( Condition cond ) {
		System.out.println();
		System.out.print(indent);
		
		System.out.print( "if " );
		cond.condition().accept(this);
		
		System.out.print(" {");

		indent();
		
		cond.onTrue().accept( this );

		if ( cond.hasElse() ) {
			System.out.println();
			
			undent();
			System.out.print(indent);
			System.out.print("} else {");
			indent();
			
			cond.onFalse().accept(this);
		}
		
		undent();
		System.out.println();
		System.out.print(indent);
		System.out.print("}");
	}

	@Override
	public void visit( ConditionalLoop loop ) {
		System.out.println();
		System.out.print(indent);

		System.out.print( "while " );
		loop.condition().accept(this);
		
		System.out.print( " {" );
		indent();
		
		loop.body().accept( this );

		undent();
		
		System.out.println();
		System.out.print(indent);
		System.out.print("}");
	}
}
