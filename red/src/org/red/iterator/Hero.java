package org.red.iterator;

public class Hero extends Actor {
	
	public Hero( ActorState state ) {
		super( state );
	}

	@Override
	public void accept(ActorVisitor visitor) {
		visitor.visit(this);
	}

	@Override
	public String label() {
		return "Hero";
	}
}
