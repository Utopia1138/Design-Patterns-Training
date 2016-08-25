package org.red.visitor.ast;

import org.red.visitor.ASTVisitor;
import org.red.visitor.interpreter.Context.Type;

public interface Expression {
	Type typeOf();
	void accept( ASTVisitor visitor );
}
