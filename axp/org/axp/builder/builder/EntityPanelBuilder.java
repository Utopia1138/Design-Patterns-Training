package org.axp.builder.builder;

import java.util.ArrayList;

public class EntityPanelBuilder<E> {
	protected ArrayList<String> headingList = new ArrayList<>();
	protected ArrayList<EntityPanelStep<E>> populateSteps = new ArrayList<>();
	protected boolean finished;
	
	public EntityPanelBuilder<E> addColumn( String heading, EntityPanelStep<E> step ) {
		if ( finished ) {
			throw new IllegalStateException( "Panel is already built!" );
		}
		
		headingList.add( heading );
		populateSteps.add( step );
		return this;
	}
	
	public EntityPanel<E> buildPanel() {
		finished = true;
		
		return new EntityPanel<E>() {
			private static final long serialVersionUID = -1053878807113372089L;
			
			@Override
			protected String[] headings() {
				return headingList.toArray( new String[] {} );
			}

			@Override
			public void addRow(E entity) {
				for ( int i = 0; i < populateSteps.size(); i++ ) {
					if ( i == populateSteps.size() - 1 ) {
						lastInRow();
					}
					
					populateSteps.get( i ).populatePanel( entity );
				}
			}
		};
	}
}
