package org.red.iterator;

public class Monster extends Actor {

	public Monster( ActorState state ) {
		super(state);
	}

	@Override
	public void accept(ActorVisitor visitor) {
		visitor.visit(this);
	}

	@Override
	public String label() {
		return "Monster";
	}
}
