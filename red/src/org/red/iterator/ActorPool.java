package org.red.iterator;

import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Stream;

public class ActorPool implements Iterable<Actor> {

	private final ActorState[] pool;

	private Consumer<ActorState> initialiser = actor -> actor
		.isAlive(true)
		.setHealth(100)
		.setX(0)
		.setY(0);

	private Consumer<ActorState> cleanup = actor -> actor.isAlive(false).setReferrent(null);
	private long changes;

	public ActorPool( int capacity ) {
		pool = new ActorState[capacity];

		for( int i = 0; i < pool.length; ++i )
			pool[i] = new ActorState(i);
	}

	public ActorPool setInitialiser( Consumer<ActorState> init ) {
		this.initialiser = init;
		return this;
	}

	public ActorPool setCleanup( Consumer<ActorState> cleanup ) {
		this.cleanup = cleanup;
		return this;
	}

	public <T extends Actor> Optional<T> instance( Function<ActorState, T> creator ) {
		Optional<ActorState> maybe = slots().filter(actor -> !actor.isAlive()).findFirst();

		if ( maybe.isPresent() ) {
			initialiser.accept(maybe.get());
			T actor = creator.apply(maybe.get());
			maybe.get().setReferrent(actor);
			changes++;
			return Optional.of(actor);
		}

		return Optional.empty();
	}

	public ActorPool release( Actor actor ) {
		if ( actor.componentId() < 0 || actor.componentId() > pool.length )
			throw new NoSuchElementException( String.format("No element for component [#%d]", actor.componentId()) );

		cleanup.accept(pool[actor.componentId()]);
		changes++;
		return this;
	}

	private Stream<ActorState> slots() {
		return Arrays.stream(pool);
	}

	public Stream<Actor> stream() {
		return slots().filter(ActorState::isAlive).map(ActorState::getReferrent);
	}

	// Old school iterator
	@Override
	public Iterator<Actor> iterator() {
		return new Iterator<Actor>() {
			private int idx;
			private final long initCount = changes;
			
			@Override
			public boolean hasNext() {
				if ( initCount != changes )
					throw new ConcurrentModificationException("Underlying actor pool has been modified since last iteration");

				for ( ; idx < pool.length ; ++idx ) {
					if ( pool[idx].isAlive() )
						return true;
				}

				return false;
			}

			@Override
			public Actor next() {
				return pool[idx++].getReferrent();
			}
		};
	}

}
