package org.axp.visitor;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

import org.axp.composite.Diet;
import org.axp.composite.Organism;
import org.axp.composite.OrganismTree;

/**
 * A collector over a stream of organisms that records the numbers of herbivores, omnivores and carnivores
 * 
 * The stream in this case can be seen as a traverser, and the collector as a visitor
 */
public class OrganismDietCollector implements Collector<OrganismTree,HashMap<Diet,Integer>,HashMap<Diet,Integer>> {

	/* Start with a fresh hashmap */
	@Override
	public Supplier<HashMap<Diet,Integer>> supplier() {
		return HashMap<Diet,Integer>::new;
	}

	/* Keep a tally of each diet (only apply to actual organisms, not categories */
	@Override
	public BiConsumer<HashMap<Diet,Integer>, OrganismTree> accumulator() {
		return (map, org) -> {
			if ( org instanceof Organism ) {
				Integer count = map.get( org.getDiet() );
				map.put( org.getDiet(), ( count == null ) ? 1 : ++count );
			}
		};
	}

	/* Combine two hashmaps */
	@Override
	public BinaryOperator<HashMap<Diet,Integer>> combiner() {
		return (map1, map2) -> {
			map1.putAll( map2 );
			return map1;
		};
	}

	/* Nothing to do to finish */
	@Override
	public Function<HashMap<Diet,Integer>, HashMap<Diet, Integer>> finisher() {
		return Function.identity();
	}

	/* Some hints as to how to use this collector */
	@Override
	public Set<Characteristics> characteristics() {
		HashSet<Characteristics> crstcs = new HashSet<>( 2 );
		crstcs.add( Characteristics.IDENTITY_FINISH );
		crstcs.add( Characteristics.CONCURRENT );
		return crstcs;
	}
}
