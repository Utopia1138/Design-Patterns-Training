package org.axp.composite;

import java.util.Collection;
import java.util.Collections;
import java.util.function.Predicate;
import java.util.stream.Stream;

/**
 * This is the Component part of the composite pattern; the common parent of {@link Organism} and {@link Category}.
 * 
 * All methods are coded to do something wherever possible, even if that means returning null or empty results.
 */
public abstract class OrganismTree {
	protected final String name;
	
	/**
	 * All organisms and categories have a name; this is final and set during construction
	 * @param name the name of the organism or category
	 */
	public OrganismTree( String name ) {
		this.name = name;
	}
	
	/**
	 * Retrieve the name
	 * @return the name of the organism or category
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * The diet of this organism (not applicable to categories)
	 * @return an enum representing the organism's typical diet, or null if this is not applicable
	 */
	public Diet getDiet() {
		return null;
	}

	/**
	 * Set the diet of this organism (not applicable to categories)
	 * @param diet the organism's typical diet, or null to unset
	 * @return a reference to this organism
	 * @throws UnsupportedOperationException if called on anything other than an organism
	 */
	public OrganismTree setDiet( Diet diet ) {
		throw new UnsupportedOperationException( "Can only set diet of a specific organism" );
	}
	
	/**
	 * The average weight of this organism (not applicable to categories)
	 * @return the average weight of this organism in kilos, or null if this is not applicable
	 */
	public Float getAvgWeight() {
		return null;
	}
	
	/**
	 * Set the average weight of this organism (not applicable to categories)
	 * @param avgWeight the average weight of this organism in kilos, or null to unset
	 * @return a reference to this organism
	 * @throws UnsupportedOperationException if called on anything other than an organism
	 */
	public Organism setAvgWeight( Float avgWeight ) {
		throw new UnsupportedOperationException( "Can only set weight of a specific organism" );
	}
	
	/**
	 * Add a new set of organisms or subcategories to the category (not applicable to organisms)
	 * @param subcategories a list of new organisms and/or categories to add beneath this category
	 * @return a reference to this category
	 * @throws UnsupportedOperationException if called on anything other than a category
	 */
	public OrganismTree add( OrganismTree...subcategories ) {
		throw new UnsupportedOperationException( "Can only add to a category" );
	}
	
	/**
	 * Recursively remove children matching a specified condition
	 * @param condition a Java 8 predicate specifying which organisms or categories to remove
	 * @return a collection of organisms or categories which were removed from the tree. For organisms, an empty set. 
	 */
	public Collection<OrganismTree> removeIf( Predicate<? super OrganismTree> condition ) {
		return Collections.emptySet();
	}

	/**
	 * Recursively find a particular organism or category by name
	 * @param name name of organism or category to find
	 * @return the matching organism/category, which could be itself, or null if it was not found
	 */
	public OrganismTree find( String name ) {
		if ( ( name != null ) && name.equals( this.name ) ) {
			return this;
		}
		
		return null;
	}
	
	/**
	 * Return this organism tree in stream form. This will be the item itself, followed by all subcategories recursively.
	 * @return a stream over the item and its subcategories
	*/
	public Stream<OrganismTree> stream() {
		return Stream.of( this );
	}
	
	/**
	 * Is this organism heavier than a particular weight, on average?
	 * @param weight a weight in kilos
	 * @return true iff this is an organism with a defined average weight which is heavier than the figure given
	 */
	public boolean heavierThan( float weight ) {
		return ( ( getAvgWeight() != null ) && ( getAvgWeight() > weight ) );
	}
	
	/**
	 * Is this organism lighter than a particular weight, on average?
	 * @param weight a weight in kilos
	 * @return true iff this is an organism with a defined average weight which is lighter than the figure given
	 */
	public boolean lighterThan( float weight ) {
		return ( ( getAvgWeight() != null ) && ( getAvgWeight() < weight ) );
	}
}
