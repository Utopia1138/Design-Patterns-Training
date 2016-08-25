package org.axp.visitor;

import org.axp.composite.Category;
import org.axp.composite.Organism;

/**
 * Interface that specifies that a visitor must accept any concrete subtype of {@link OrganismTree}.
 *
 * @param <E>
 */
public interface OrganismTreeVisitor<E> {
	/**
	 * Deal with a category
	 * @param category
	 * @param state
	 */
	public void accept( Category category, E state );
	
	/**
	 * Deal with a specific organism
	 * @param organism
	 * @param state
	 */
	public void accept( Organism organism, E state );
}
