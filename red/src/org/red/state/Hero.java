package org.red.state;

import java.util.concurrent.atomic.AtomicBoolean;

public class Hero {
	private HeroState state = new RestState(this);
	private AtomicBoolean running = new AtomicBoolean(false);
	private static final long tickInterval = 50L;
	
	public synchronized void attack() {
		this.state = state.attack();
	}

	public synchronized void defend() {
		this.state = state.defend();
	}

	public synchronized void jump() {
		this.state = state.jump();
	}

	public void start() {
		new Thread( () -> {
			running.set( true );
			while( running.get() ) {
				synchronized( this ) {
					this.state = state.tick();
				}

				try {
					Thread.sleep( tickInterval );
				}
				catch ( Exception e ) { /* Whatevs */ }
			}
		}).start();
	}

	public void stop() {
		running.set( false );
	}
}
