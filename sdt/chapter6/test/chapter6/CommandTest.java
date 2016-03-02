package chapter6;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import chapter6.commands.Attack;
import chapter6.commands.Command;
import chapter6.commands.Defend;
import chapter6.location.Location;

public class CommandTest {

	@Test
	public void testListOfCommands() {
		List<Command> commands = new ArrayList<Command>();

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

		Army f1 = new Army();
		f1.setCountry(Country.FRANCE);
		f1.setExperience(2);
		f1.setStrength(1);
		f1.setSize(900_000);

		Location b = new Location();
		b.setName("Berlin");
		b.setOwnedBy(Country.GERMANY);

		Defend c1 = new Defend();
		c1.setArmy(g1);
		c1.setLocation(b);
		commands.add(c1);

		Defend c2 = new Defend();
		c2.setArmy(g2);
		c2.setLocation(b);
		commands.add(c2);

		Attack c3 = new Attack();
		c3.setArmy(f1);
		c3.setLocation(b);
		commands.add(c3);

		for( Command c : commands){
			c.execute();
		}
		
	}

}
