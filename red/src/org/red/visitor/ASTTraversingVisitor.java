package org.red.visitor;

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

public class ASTTraversingVisitor implements ASTVisitor {
	private final ASTVisitor visitor;
	
	public ASTTraversingVisitor( ASTVisitor visitor ) {
		this.visitor = visitor;
	}

	@Override
	public void visit( DeclarationStatement decl ) {
		decl.accept( visitor );
	}

	@Override
	public void visit( Constant constant ) {
		constant.accept( visitor );
	}

	@Override
	public void visit( UnaryOperator op ) {
		op.accept( visitor );
		op.input().accept(this);
	}

	@Override
	public void visit( BinaryOperator op ) {
		op.accept( visitor );
		op.lhs().accept( this );
		op.rhs().accept( this );
	}

	@Override
	public void visit( LookupExpression lu ) {
		lu.accept( visitor );
	}

	@Override
	public void visit( AssignmentStatement assign ) {
		assign.accept( visitor );
		assign.assignmentExpr().accept( this );
	}

	@Override
	public void visit( Terminal term ) {
		term.expr().accept( this );
		term.accept( visitor );
	}

	@Override
	public void visit( Sequence seq ) {
		seq.accept( visitor );
		seq.forEach( s -> s.accept(this) );
	}

	@Override
	public void visit(Condition condition) {
		condition.accept( visitor );
		condition.condition().accept( this );
		condition.onTrue().accept( this );
		
		if ( condition.hasElse() )
			condition.onFalse().accept( this );
	}

	@Override
	public void visit(ConditionalLoop loop) {
		loop.accept( visitor );
		loop.condition().accept( this );
		loop.body().accept( this );
	}
}
