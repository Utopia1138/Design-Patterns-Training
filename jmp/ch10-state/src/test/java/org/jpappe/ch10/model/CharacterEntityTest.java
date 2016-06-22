package org.jpappe.ch10.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.jpappe.ch10.state.HealthStateFactory;
import org.junit.Test;

public class CharacterEntityTest {

	// we're comparing doubles so we need a margin of error.
	private static final double DELTA = 0.01;
	
	@Test
	public void testHealthState() {
		HealthStateFactory stateFactory = new HealthStateFactory();
		CharacterEntity character = new CharacterEntity(stateFactory, 100, 100);
		
		// verify the character is at full health
		assertEquals(110, character.getEffectiveness(), DELTA);
		
		// the character takes damage, but they should still be healthy
		character.receiveAction(c -> { c.getCurrentState().takeDamage(8); });
		assertEquals(110, character.getEffectiveness(), DELTA);
		
		// take more damage, and now injured
		character.receiveAction(c -> { c.getCurrentState().takeDamage(12); });
		assertEquals(75, character.getEffectiveness(), DELTA);
		
		// become gravely inured
		character.receiveAction(c -> { c.getCurrentState().takeDamage(60); });
		assertEquals(50, character.getEffectiveness(), DELTA);
		
		// become unconscious
		character.receiveAction(c -> { c.getCurrentState().takeDamage(15);});
		assertEquals(0, character.getEffectiveness(), DELTA);
		
		// now be dead!
		character.receiveAction(c -> { c.getCurrentState().takeDamage(100);});
		// make sure health can't go below 0
		assertEquals(0, character.getCurrentHealth());
		assertEquals(0, character.getEffectiveness(), DELTA);
		
		// healing does nothing when you're dead
		character.receiveAction(c -> {c.getCurrentState().heal(95);});
		// health is still at 0
		assertEquals(0, character.getCurrentHealth());
		
		// but if we can revive him back to Healthy
		character.receiveAction(c-> {c.getCurrentState().revive(95);});
		assertEquals(95, character.getCurrentHealth());
		assertEquals(110, character.getEffectiveness(), DELTA);
	}

}
