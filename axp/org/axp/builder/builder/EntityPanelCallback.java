package org.axp.builder.builder;

@FunctionalInterface
interface EntityPanelCallback<V> {
	public abstract void doCallback( V value );
}
