package org.axp.builder.builder;

@FunctionalInterface
interface EntityPanelStep<E> {
	public abstract void populatePanel( EntityPanel<E> panel, E entity );
}
