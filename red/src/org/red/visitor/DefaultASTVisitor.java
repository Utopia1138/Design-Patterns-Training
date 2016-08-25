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

public interface DefaultASTVisitor extends ASTVisitor {
	default void visit( DeclarationStatement decl ) {}
	default void visit( Constant constant ) {}
	default void visit( UnaryOperator unaryOperator ) {}
	default void visit( BinaryOperator binaryOperator ) {};
	default void visit( LookupExpression lookupExpression ) {}
	default void visit( AssignmentStatement assign ) {}
	default void visit( Terminal terminal ) {}
	default void visit( Sequence sequence ) {}
	default void visit(Condition condition) {}
	default void visit(ConditionalLoop conditionalLoop) {}
}
