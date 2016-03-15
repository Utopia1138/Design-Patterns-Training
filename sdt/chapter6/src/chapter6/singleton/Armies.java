package chapter6.singleton;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import chapter6.Army;
import chapter6.Country;
import chapter6.Location;

public class Armies {
	private static List<Army> armies = new ArrayList<>();

	private Armies() {
		// Singleton;
	}

	public static List<Army> getInstance() {
		return armies;
	}

	public static void addArmy(Army army) {
		armies.add(army);
	}

	public static List<Army> getCountrysArmies(Country country) {
		List<Army> countryArmies = new ArrayList<>();

		for (Army army : armies) {
			if (army.getCountry() == country) {
				countryArmies.add(army);
			}
		}

		return countryArmies;
	}

	public static List<Army> getDefendingForces(Location location) {
		List<Army> defenders = new ArrayList<Army>();
		for (Army army : armies) {
			if (army.getLocation() != null && army.getLocation().equals(location)) {
				defenders.add(army);
			}
		}
		return defenders;
	}
	
	public static void purgeDeadArmies(){
		List<Army> livingArmies = armies.stream().filter( army -> !army.isDead() ).collect(Collectors.toList());
		armies = livingArmies;
	}

}
