package chapter6.battle;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import chapter6.Army;
import chapter6.location.Location;

/**
 * Responsible for determining the outcome of a single battle at a given
 * location. To the death, naturally.
 * 
 * @author Sandy
 *
 */
public class BattleSimulator {

	Random r = new Random();

	public Location attack(Army attacker, Location location) {

		System.out.println(location.getName() + " (Owned by "
				+ location.getOwnedBy() + ") is being attacked by "
				+ attacker.getCountry());

		List<Army> defenders = location.getDefendingForces();

		// Hackity hack hack
		attacker.setSizeAtStartOfBattle(attacker.getSize());
		for( Army defender : defenders ){
			defender.setSizeAtStartOfBattle(defender.getSize());
		}
		
		while (!isBattleWon(attacker, defenders)) {
			
			attacker.setStrength(calculateArmyStrength(attacker));
			int totalDefenseStrength = 0;
			for (Army defender : defenders) {
				defender.setStrength(calculateArmyStrength(defender));
				totalDefenseStrength += defender.getStrength();
			}

			attacker = calculateAttrition(attacker, attacker.getStrength(),
					totalDefenseStrength, attacker.getSizeAtStartOfBattle() / 10);
			for (Army defender : defenders) {
				defender = calculateAttrition(defender, defender.getStrength(),
						attacker.getStrength(), defender.getSizeAtStartOfBattle() / 10);
			}

		}

		if (attacker.getSize() > 0) {
			// Attacker has won, so update location stuff
			location.setDefendingForces(Arrays.asList(attacker));
			location.setOwnedBy(attacker.getCountry());
			System.out.println(attacker.getCountry() + " won the battle");
		} else {
			System.out.println("Defenders held out");
		}

		return location;
	}

	private boolean isBattleWon(Army attacker, List<Army> defenders) {
		if (attacker.getSize() == 0) {
			return true;
		}

		for (Army defender : defenders) {
			if (defender.getSize() != 0) {
				return false;
			}
		}

		return true;

	}

	private Army calculateAttrition(Army army, double armyStrength,
			double opposingStrength, double sizeGambled) {

		int size = army.getSize();
		if (sizeGambled > size) {
			sizeGambled = size;
		}

		int lowWaterMark = (int) (size - sizeGambled);

		double attritionFactor;

		if (opposingStrength > armyStrength) {
			attritionFactor = armyStrength / opposingStrength;
		} else {
			attritionFactor = 1 - (1 / (armyStrength / opposingStrength));
		}

		int newSize = (int) (lowWaterMark + (sizeGambled * attritionFactor));

		System.out.println(army.getCountry() + " lost " + (size - newSize)
				+ " troops ( " + newSize + " left )");

		army.setSize(newSize);

		return army;
	}

	private int calculateArmyStrength(Army army) {

		double luck = r.nextDouble();

		int strength = (int) (army.getSize() * army.getExperience() * luck);
		return strength;
	}

}
