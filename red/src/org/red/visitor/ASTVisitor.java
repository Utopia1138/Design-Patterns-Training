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
import org.red.visitor.ast.binop.AdditionOperator;
import org.red.visitor.ast.binop.DivisionOperator;
import org.red.visitor.ast.binop.GreaterThanOperator;
import org.red.visitor.ast.binop.LessThanOperator;
import org.red.visitor.ast.binop.MultiplicationOperator;
import org.red.visitor.ast.binop.SubtractionOperator;

public interface ASTVisitor {
	void visit( DeclarationStatement decl );
	void visit( Constant constant );
	void visit( UnaryOperator unaryOperator );
	void visit( LookupExpression lookup );
	void visit( AssignmentStatement assign );
	void visit( Terminal terminal );
	void visit( Sequence sequence );
	void visit( Condition condition );
	void visit( ConditionalLoop loop );
	void visit( BinaryOperator binaryOperator );

	// Most implementations won't need this level of type detail
	default void visit( AdditionOperator add ) { visit((BinaryOperator)add); }
	default void visit( SubtractionOperator sub )  { visit((BinaryOperator)sub); }
	default void visit( DivisionOperator div ) { visit((BinaryOperator)div); }
	default void visit( MultiplicationOperator multi ) { visit((BinaryOperator)multi); }
	default void visit( GreaterThanOperator greater ) { visit((BinaryOperator)greater); }
	default void visit( LessThanOperator less ) { visit((BinaryOperator)less); }
}
