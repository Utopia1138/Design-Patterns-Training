package org.red.iterator;

public abstract class Actor {
	private final ActorState state;

	public Actor( ActorState state ) {
		this.state = state;
	}
	
	abstract void accept( ActorVisitor visitor );
	abstract String label();

	public int componentId() {
		return state.getComponentId();
	}

	public void takeDamage( int damage ) {
		state.setHealth(state.getHealth() - damage);
	}

	public int getHealth() {
		return state.getHealth();
	}

	@Override
	public String toString() {
		return String.format("[%02d] %-12s %03d", componentId(), label(), getHealth());
	}
}
