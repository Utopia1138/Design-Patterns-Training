package org.red.visitor.ast.binop;

import org.red.visitor.ASTVisitor;
import org.red.visitor.ast.BinaryOperator;
import org.red.visitor.ast.Expression;

public class GreaterThanOperator extends BinaryOperator {

	public GreaterThanOperator(Expression lhs, Expression rhs) {
		super(">", lhs, rhs);
	}

	@Override
	public void accept(ASTVisitor visitor) {
		visitor.visit(this);
	}
}
