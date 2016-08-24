package org.red.visitor.ast;

import org.red.visitor.ASTVisitor;
import org.red.visitor.Context;
import org.red.visitor.Context.Type;
import org.red.visitor.Context.Value;

public interface Expression {
	Value execute( Context context );
	Type typeOf();
	void accept( ASTVisitor visitor );
}
