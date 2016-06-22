package chapter6;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import chapter6.commands.Attack;
import chapter6.commands.Command;
import chapter6.commands.Defend;
import chapter6.commands.Recouperate;
import chapter6.input.ConsoleSelection;
import chapter6.singleton.Armies;
import chapter6.singleton.Locations;

public class TurnManager {
		
	private Scanner scanner = new Scanner(System.in);
	private ConsoleSelection<Command> commandSelector = new ConsoleSelection<>( scanner );
	private ConsoleSelection<Army> armySelector = new ConsoleSelection<>( scanner );
	private ConsoleSelection<Location> locationSelector = new ConsoleSelection<>( scanner );

	public void start() {

		while (!isWarWon()) {
			for (Country country : Country.values()) {
				System.out.println("=====" + country + " TURN =====");
				List<Command> commands = getCommandsForCountry(country);
				commands.forEach(command -> command.execute());
			}
		}

		System.out.println(	Locations.getLocations().get(0).getOwnedBy() + " wins!" );
	}

	private List<Command> getCommandsForCountry(Country country) {
		List<Army> armiesWithNoCommands = Armies.getCountrysArmies(country);
		List<Command> commands = new ArrayList<>();

		while (armiesWithNoCommands.size() > 0) {
			Army armyChosen = armySelector
					.selectObjectFromList(armiesWithNoCommands);

			System.out.println("Select what this army should do");
			Command command = commandSelector.selectObjectFromList(Arrays
					.asList(new Attack(), new Defend(), new Recouperate()));
			command.setArmy(armyChosen);
			command.setLocation(locationSelector
					.selectObjectFromList(Locations.getLocations()));
			commands.add(command);
			armiesWithNoCommands.remove(armyChosen);
		}

		return commands;
	}

	private boolean isWarWon() {
		return Locations.isAllLocationsOwnedByOneCountry();
	}

}
