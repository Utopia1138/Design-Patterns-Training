package org.jpappe.ch10.state;

import org.jpappe.ch10.model.CharacterEntity;

public class HealthStateFactory {
	
	public HealthState getHealthStateForCharacter(CharacterEntity c ) {
		
		double percent = ((double) c.getCurrentHealth()) / c.getMaxHealth();
		
		if ( percent >= 0.9 ) {
			return new Healthy(c, this);
		}
		
		
		return null;
	}

}
