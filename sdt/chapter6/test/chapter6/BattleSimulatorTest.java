package chapter6;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.junit.Test;

import chapter6.battle.BattleSimulator;
import chapter6.location.Location;

public class BattleSimulatorTest {

	BattleSimulator bs = new BattleSimulator();

	@Test
	public void AttackNoDefendingForcesTest() {

		Location b = new Location();
		b.setName("Berlin");
		b.setOwnedBy(Country.GERMANY);

		Army a = new Army();
		a.setCountry(Country.FRANCE);
		a.setExperience(1);
		a.setStrength(1);
		a.setSize(1_000_000);

		Location outcome = bs.attack(a, b);
		assertEquals(outcome.getOwnedBy(), Country.FRANCE);
	}
	
	
	@Test
	public void AttackOneDefendingArmyTest() {

		Army g = new Army();
		g.setCountry(Country.GERMANY);
		g.setExperience(1);
		g.setStrength(1);
		g.setSize(1_000_000);
		
		Location b = new Location();
		b.setName("Berlin");
		b.setOwnedBy(Country.GERMANY);
		b.setDefendingForces(Arrays.asList(g));

		Army a = new Army();
		a.setCountry(Country.FRANCE);
		a.setExperience(1);
		a.setStrength(1);
		a.setSize(1_000_000);

		Location outcome = bs.attack(a, b);
		assertEquals(outcome.getOwnedBy(), Country.FRANCE);
	}
	
	
	@Test
	public void AttackTwoDefendingArmiesTest() {

		Army g1 = new Army();
		g1.setCountry(Country.GERMANY);
		g1.setExperience(1);
		g1.setStrength(1);
		g1.setSize(1_000_000);
		
		Army g2 = new Army();
		g2.setCountry(Country.GERMANY);
		g2.setExperience(1);
		g2.setStrength(2);
		g2.setSize(1_000_000);
		
		Location b = new Location();
		b.setName("Berlin");
		b.setOwnedBy(Country.GERMANY);
		b.setDefendingForces(Arrays.asList(g1, g2));

		Army a = new Army();
		a.setCountry(Country.FRANCE);
		a.setExperience(1);
		a.setStrength(1);
		a.setSize(1_000_000);

		Location outcome = bs.attack(a, b);
		assertEquals(outcome.getOwnedBy(), Country.FRANCE);
	}

}
