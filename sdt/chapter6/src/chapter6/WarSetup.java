package chapter6;

import java.util.Arrays;
import java.util.Scanner;

import chapter6.input.StringCapturer;
import chapter6.input.ConsoleSelection;
import chapter6.singleton.Armies;
import chapter6.singleton.Locations;

public class WarSetup {

	private Scanner scanner = new Scanner(System.in);
	private StringCapturer stringCapturer = new StringCapturer(scanner);
	private ConsoleSelection<Location> locationSelector = new ConsoleSelection<>(
			scanner);
	private ConsoleSelection<Country> countrySelector = new ConsoleSelection<>(
			scanner);
	private ConsoleSelection<Boolean> booleanSelector = new ConsoleSelection<>(
			scanner);

	public void setupLocations() {

		boolean addAnotherLocation = true;

		System.out.println("SETUP LOCATIONS!");

		while (addAnotherLocation) {

			Location l = new Location();
			System.out.println("Choose country");
			l.setOwnedBy(countrySelector.selectObjectFromList(Arrays
					.asList(Country.values())));
			System.out.println("Enter location name");
			l.setName(stringCapturer.captureString());

			Locations.addLocation(l);

			System.out.println("Add another location?");
			addAnotherLocation = booleanSelector.selectObjectFromList(Arrays
					.asList(true, false));
		}
	}

	public void setupArmies() {
		boolean addAnotherArmy = true;

		System.out.println("SETUP ARMIES!");

		while (addAnotherArmy) {

			Army a = new Army();
			System.out.println("Choose country");
			a.setCountry(countrySelector.selectObjectFromList(Arrays
					.asList(Country.values())));
			System.out.println("Enter size (int please!)");
			String sizeAsString = stringCapturer.captureString();
			int size = Integer.valueOf(sizeAsString);
			a.setSize(size);
			System.out.println("Select initial location");
			a.setLocation(locationSelector.selectObjectFromList(Locations
					.getLocationsOwnedByCountry(a.getCountry())));
			Armies.addArmy(a);

			System.out.println("Add another army?");
			addAnotherArmy = booleanSelector.selectObjectFromList(Arrays
					.asList(true, false));
		}
	}

}
