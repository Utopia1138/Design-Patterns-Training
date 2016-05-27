package org.axp.composite;

/**
 * This is the Leaf part of the composite pattern.
 * 
 * Extra methods are implemented to get and set features of specific organisms.
 */
public class Organism extends OrganismTree {
	private Float avgWeight;
	private Diet diet;
	
	public Organism( String name ) {
		super( name );
	}
	
	@Override
	public Diet getDiet() {
		return diet;
	}

	@Override
	public Organism setDiet(Diet diet) {
		this.diet = diet;
		return this;
	}
	
	@Override
	public Float getAvgWeight() {
		return avgWeight;
	}
	
	@Override
	public Organism setAvgWeight( Float avgWeight ) {
		this.avgWeight = avgWeight;
		return this;
	}
}
