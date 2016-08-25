package org.red.visitor.ast;

import org.red.visitor.interpreter.Context.Type;

public interface Statement extends Expression {
	@Override
	default Type typeOf() {
		return Type.UNIT;
	}
}
