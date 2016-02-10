package org.axp.adaptor;

import static org.junit.Assert.*;

import org.axp.adaptor.ducks.MallardDuck;
import org.axp.adaptor.ducks.Turducken;
import org.axp.adaptor.ducks.Turkey;
import org.junit.Test;

/**
 * Tests for the "Ducks" examples, showcasing our {@link Turkducken} class adaptor in particular.
 */
public class TestDucks {

	@Test
	public void testMallardDuck() {
		MallardDuck donald = new MallardDuck();
		assertEquals( "Mallard quacking", "Quack, quack!", donald.quack() );
		assertEquals( "Mallard flying", "Whee, I'm flying", donald.fly() );
	}

	@Test
	public void testTurkey() {
		Turkey dustin = new Turkey();
		assertEquals( "Turkey gobbling", "Gobble gobble gobble", dustin.gobble() );
		assertEquals( "Turkey flying", "*Flaps furiously*", dustin.fly() );
	}
	
	@Test
	public void testTurducken() {
		Turducken donstin = new Turducken();
		assertEquals( "Turducken gobbling", "Gobble gobble gobble", donstin.gobble() );
		assertEquals( "Turducken quacking", "Gobble gobble gobble", donstin.quack() );
		assertEquals( "Turducken flying", "*Flaps furiously*\n*Flaps furiously*\n*Flaps furiously*", donstin.fly() );
	}
}
