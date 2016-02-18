/**
 * 
 */
package org.mssw.decorator;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.SwingUtilities;

import org.mssw.ui.Screen;

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
	 * @throws IOException 
	 */
	public static void main(String[] args)
			throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException,
			NoSuchMethodException, SecurityException, InterruptedException, IOException {

		Generator gen = new Generator();
		

		List<Combatent> combatents = new ArrayList<>();
		combatents.add(gen.generateCombatent());
		combatents.add(gen.generateCombatent());

		for (Combatent c : combatents) {
			System.out.println(c.getName() + " Entered the arena (" + c.getHealth() +"hp)");
			System.out.println(c.getName() + " picked up a " + c.getWeapon().getDescription());
		}
		int distance = 150;
		
		Screen screen = new Screen(combatents, distance);
		new Thread(screen).start();

		Thread.sleep(5000);
		System.out.println("Battle commencing in: ");
		System.out.println("3...");
		Thread.sleep(1000);
		System.out.println("2...");
		Thread.sleep(1000);
		System.out.println("1...");
		Thread.sleep(1000);
		System.out.println("Go!");
		boolean winner = false;
		while (!winner) {
			for (Combatent c : combatents) {
				Combatent target = c;
				while (c.equals(target) && gen.chance() < 99) {
					target = combatents.get(gen.chance(combatents.size()));
				}

				switch (c.shoot(target, distance, gen.chance())) {
				case OUT_OF_RANGE:
					screen.updateDistance(distance -= c.getSpeed());
					System.out.println(c.getName() + " is closing in on " + target.getName() + "(" + distance+" paces)");
					break;
				case RELOADING:
					System.out.println(c.getName() + " is reloading...");
					break;
				case HIT:
					if (c.equals(target)) {
						System.out.println(c.getName() + " shot himself with his " + c.getWeapon().getDescription());
						c.fail();
						Thread.sleep(1000);
						System.out.print(".");
						Thread.sleep(1000);
						System.out.print(".");
						Thread.sleep(1000);
						System.out.println(".");
						Thread.sleep(1000);
					} else {
						System.out.println(c.getName() + " shot at " + target.getName() + (c.alive()? "" : " from the grave ") + " with his "
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
			screen.tick();
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
