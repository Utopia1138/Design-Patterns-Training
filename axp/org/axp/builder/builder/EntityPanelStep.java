package org.axp.builder.builder;

@FunctionalInterface
interface EntityPanelStep<E> {
	public abstract void populatePanel( E entity );
}
