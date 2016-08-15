package org.axp.builder.builder;

import java.util.ArrayList;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class EntityPanelBuilder<E> {
	protected ArrayList<String> headingList = new ArrayList<>();
	protected ArrayList<Consumer<E>> populateSteps = new ArrayList<>();
	protected boolean finished;
	protected Consumer<String> printFn;
	protected Supplier<E> generatorFn;
	
	private void checkNotFinished() {
		if ( finished ) {
			throw new IllegalStateException( "Panel is already built!" );
		}
	}
	
	public EntityPanelBuilder<E> addColumn( String heading, Consumer<E> step ) {
		checkNotFinished();
		headingList.add( heading );
		populateSteps.add( step );
		return this;
	}

	public EntityPanelBuilder<E> setPrintFunction( Consumer<String> printFn ) {
		checkNotFinished();
		this.printFn = printFn;
		return this;
	}

	public EntityPanelBuilder<E> setGenerateFunction( Supplier<E> generatorFn ) {
		checkNotFinished();
		this.generatorFn = generatorFn;
		return this;
	}
	
	public EntityPanel<E> buildPanel() {
		finished = true;
		
		return new EntityPanel<E>( printFn, generatorFn ) {
			private static final long serialVersionUID = -1053878807113372089L;
			
			@Override
			protected String[] headings() {
				return headingList.toArray( new String[] {} );
			}

			@Override
			protected void addRowForEntity(E entity) {
				for ( int i = 0; i < populateSteps.size(); i++ ) {
					if ( i == populateSteps.size() - 1 ) {
						lastInRow();
					}
					
					populateSteps.get( i ).accept( entity );
				}
			}
		};
	}
}
