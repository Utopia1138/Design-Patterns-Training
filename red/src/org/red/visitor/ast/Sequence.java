package org.red.visitor.ast;

import java.util.ArrayList;
import java.util.List;

import org.red.visitor.ASTVisitor;
import org.red.visitor.Context;
import org.red.visitor.Context.Value;

public class Sequence implements Statement {

	private List<Statement> program = new ArrayList<>();
	
	@Override
	public Value execute( Context context ) {
		for ( Statement stmt : program ) {
			stmt.execute( context );
		}

		return Value.VOID;
	}

	@Override
	public void accept( ASTVisitor visitor ) {
		visitor.visitPre( this );
		program.forEach( s -> s.accept( visitor ) );
		visitor.visitPost( this );
	}

	public Sequence append( Statement stmt ) {
		this.program.add( stmt );
		return this;
	}

}
