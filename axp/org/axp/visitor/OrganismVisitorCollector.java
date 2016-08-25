package org.axp.visitor;

import java.util.function.BiConsumer;
import java.util.stream.Collector;

import org.axp.composite.OrganismTree;

/**
 * Specifies that this object must be both a collector and visitor for the {@link OrganismTree}
 */
public interface OrganismVisitorCollector<E,F> extends Collector<OrganismTree,E,F>, OrganismTreeVisitor<E> {
	/**
	 * Accumulate by visiting elements, passing state as an argument
	 */
	default public BiConsumer<E, OrganismTree> accumulator() {
		return ( state, org ) -> { org.visit( this, state ); };
	}
}
