package org.axp.visitor;

/**
 * This is the interface that specifies that all parts of an {@link OrganismTree} must be visitable
 */
public interface OrganismTreeVisitable {
	/**
	 * Deal with a visit from the visitor by making the visitor accept this object
	 * 
	 * @param visitor a visitor which will accept this object
	 * @param state any state which the visitor needs to operate upon
	 */
	public <E> void visit( OrganismTreeVisitor<E> visitor, E state );
}
