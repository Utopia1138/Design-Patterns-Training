package org.red.iterator;

public class ActorState {
	private final int componentId;
	private Actor ref;
	private int x, y;
	private int health;
	private boolean alive;
	
	// ... a bunch more state ...
	
	public ActorState(int id) {
		this.componentId = id;
	}
	
	public ActorState setX( int x ) {
		this.x = x;
		return this;
	}

	public ActorState setY( int y ) {
		this.y = y;
		return this;
	}

	public ActorState setHealth( int health ) {
		this.health = health;
		return this;
	}

	public ActorState isAlive( boolean alive ) {
		this.alive = alive;
		return this;
	}

	public ActorState setReferrent( Actor actor ) {
		this.ref = actor;
		return this;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getHealth() {
		return health;
	}

	public boolean isAlive() {
		return alive;
	}

	public Actor getReferrent() {
		return ref;
	}

	public int getComponentId() {
		return this.componentId;
	}
}
