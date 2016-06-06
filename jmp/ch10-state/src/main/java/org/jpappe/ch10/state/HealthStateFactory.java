package org.jpappe.ch10.state;

import org.jpappe.ch10.model.CharacterEntity;

public class HealthStateFactory {
	
	public HealthState getHealthStateForCharacter(CharacterEntity c ) {
		
		
		
		double percent = ((double) c.getCurrentHealth()) / c.getMaxHealth();
		
		// health is above 90% => Healthy
		if ( percent >= 0.9 ) {
			return new Healthy(c, this);
		}
		// (90% > health >= 50%) => Inured
		else if ( percent >= 0.5 ) {
			return new Injured(c, this);
		}
		// (50% > health >= 10%) => Gravely Injured
		else if ( percent >= 0.1 ) {
			return new GravelyInjured(c, this);
		}
		// (10% > health >= 0) => Unconscious
		else if ( percent > 0d ) {
			return new Unconscious(c, this);
		}
		
		// health == 0 => Dead
		return new Dead(c, this);
	}

}
