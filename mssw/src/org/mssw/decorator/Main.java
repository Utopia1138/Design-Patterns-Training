/**
 * 
 */
package org.mssw.decorator;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Mike
 *
 */
public class Main {

	/**
	 * @param args
	 * @throws SecurityException
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 * @throws InterruptedException
	 */
	public static void main(String[] args)
			throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException,
			NoSuchMethodException, SecurityException, InterruptedException {

		Generator gen = new Generator();

		List<Combatent> combatents = new ArrayList<>();
		combatents.add(gen.generateCombatent());
		combatents.add(gen.generateCombatent());

		for (Combatent c : combatents) {
			System.out.println(c.getName() + " picked up a " + c.getWeapon().getDescription());
		}

		int distance = 100;
		boolean winner = false;
		while (!winner) {
			for (Combatent c : combatents) {
				Combatent target = c;
				while (c.equals(target) || gen.chance() < 99) {
					target = combatents.get(gen.chance(combatents.size()));
				}

				switch (c.shoot(target, distance, gen.chance())) {
				case CLOSING_IN:
					System.out.println(c.getName() + " is closing in on " + target.getName());
					distance -= 2;
					break;
				case RELOADING:
					System.out.println(c.getName() + " is reloading...");
					break;
				case HIT:
					if (c.equals(target)) {
						System.out.println(c.getName() + " shot himself with his " + c.getWeapon().getDescription());
					} else {
						System.out.println(c.getName() + " shot at " + target.getName() + " with his "
								+ c.getWeapon().getDescription());
					}
					int targetHealth = target.getHealth();
					if (targetHealth <= 0) {
						System.out.println(target.getName() + " died...");
						winner = true;
					} else {
						System.out.println(target.getName() + " now has " + target.getHealth() + "hp");
					}
					break;
				case MISS:
					System.out.println(c.getName() + " shot at " + target.getName() + " but missed!");
				}
			}
			Thread.sleep(500);
		}
	}

	public static int[][] setStartingDistances(int size) {
		int distances[][] = new int[size][size];
		for (int x = 0; x < size; x++) {
			for (int y = 0; y < size; y++) {
				distances[x][y] = 150;
			}
		}

		return distances;
	}

}
