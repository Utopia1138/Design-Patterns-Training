package org.red.visitor.ast;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import org.red.visitor.ASTVisitor;

public class Sequence implements Statement {

	private List<Statement> seq = new ArrayList<>();

	@Override
	public void accept( ASTVisitor visitor ) {
		visitor.visit( this );
	}

	public Sequence append( Statement stmt ) {
		this.seq.add( stmt );
		return this;
	}

	public void forEach( Consumer<Statement> consumer ) {
		seq.forEach(consumer);
	}

	public List<Statement> statements() {
		return seq;
	}

}
