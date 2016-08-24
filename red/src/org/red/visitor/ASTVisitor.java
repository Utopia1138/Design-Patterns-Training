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

public interface ASTVisitor {
	default void visit( DeclarationStatement decl ) {}
	default void visit( Constant constant ) {}
	default void visit( UnaryOperator unaryOperator ) {}
	default void visitBefore( BinaryOperator binaryOperator ) {};
	default void visitBetween( BinaryOperator binaryOperator ) {};
	default void visitAfter( BinaryOperator op ) {};
	default void visit( LookupExpression lookupExpression ) {}
	default void visitPre( AssignmentStatement assign ) {}
	default void visitPost( AssignmentStatement assign ) {}
	default void visit( Terminal terminal ) {}
	default void visitPre( Sequence sequence ) {}
	default void visitPost( Sequence sequence ) {}
	default void visitBefore(Condition condition) {};
	default void visitBeforeStatements(Condition condition) {};
	default void visitAfter(Condition condition) {};
}
