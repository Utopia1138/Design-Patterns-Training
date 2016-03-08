package chapter6;

import java.util.List;
import java.util.Random;

import chapter6.singleton.Armies;

/**
 * Responsible for determining the outcome of a single battle at a given
 * location. To the death, naturally.
 * 
 * @author Sandy
 *
 */
public class BattleSimulator {

	Random r = new Random();

	public void attack(Army attacker, Location location) {

		System.out.println(location.getName() + " (Owned by "
				+ location.getOwnedBy() + ") is being attacked by "
				+ attacker.getCountry());

		List<Army> defenders = Armies.getDefendingForces(location);

		while (!isBattleWon(attacker, defenders)) {

			try {
				System.out.println();
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// Swallow
			}
			
			attacker.setStrength(calculateArmyStrength(attacker));
			int totalDefenseStrength = 0;
			for (Army defender : defenders) {
				defender.setStrength(calculateArmyStrength(defender));
				totalDefenseStrength += defender.getStrength();
			}

			attacker = calculateAttrition(attacker, attacker.getStrength(),
					totalDefenseStrength, attacker.getInitialSize() / 10);
			for (Army defender : defenders) {
				defender = calculateAttrition(defender, defender.getStrength(),
						attacker.getStrength(), defender.getInitialSize() / 10);
			}

		}

		if (attacker.getSize() > 0) {

			attacker.setExperience(attacker.getExperience() + 1); // Level up!

			// Attacker has won, so update location stuff
			location.setOwnedBy(attacker.getCountry());
			attacker.setLocation(location);
			System.out.println(attacker.getCountry() + " won the battle");
		} else {
			System.out.println("Defenders held out");
			for (Army defender : defenders) {
				defender.setExperience(defender.getExperience() + 1); // Level
																		// up!
			}
		}
		
		Armies.purgeDeadArmies();
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
