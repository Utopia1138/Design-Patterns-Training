/**
 * 
 */
package org.mssw.decorator;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.mssw.decorator.weapon.Rifle;
import org.mssw.decorator.weapon.RocketLauncher;
import org.mssw.decorator.weapon.SMG;
import org.mssw.decorator.weapon.Shotgun;
import org.mssw.decorator.weapon.mod.DoubleBarrel;
import org.mssw.decorator.weapon.mod.Rusty;
import org.mssw.decorator.weapon.mod.SniperScope;
import org.mssw.decorator.weapon.mod.Silencer;

/**
 * @author Mike
 *
 */
public class Generator {

	ArrayList<Weapon> baseWeapons;
	ArrayList<Combatent> combatents;
	Random random = new Random();

	public Generator() {
		initialiseBaseWeapons();
		initialiseCombatents();
	}

	public void initialiseBaseWeapons() {
		baseWeapons = new ArrayList<>();
		baseWeapons.add(new Rifle());
		baseWeapons.add(new Shotgun());
		baseWeapons.add(new RocketLauncher());
		baseWeapons.add(new SMG());
	}

	public void initialiseCombatents() {
		combatents = new ArrayList<>();
		combatents.add(new Combatent("Axp"));
		combatents.add(new Combatent("Big"));
		combatents.add(new Combatent("Jump"));
		combatents.add(new Combatent("Mssw"));
		combatents.add(new Combatent("Red"));
		combatents.add(new Combatent("Rpi"));
		combatents.add(new Combatent("Sdt"));
		combatents.add(new Combatent("Spg"));
		combatents.add(new Combatent("Txr"));
		combatents.add(new Combatent("Wpb"));
	}

	public List<Class<? extends Weapon>> createModSet() {
		List<Class<? extends Weapon>> mods = new ArrayList<>();
		mods.add(DoubleBarrel.class);
		mods.add(SniperScope.class);
		mods.add(Silencer.class);
		mods.add(Rusty.class);

		return mods;
	}

	public Weapon generateWeapon() throws InstantiationException, IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, NoSuchMethodException, SecurityException {
		int size = baseWeapons.size();
		if (size == 0) {
			throw new RuntimeException("Run out of base weapons!");
		}

		Weapon weapon = null;
		while (weapon == null) {
			int index = random.nextInt(size);
			weapon = baseWeapons.remove(index);
		}

		List<Class<? extends Weapon>> mods = createModSet();
		int addModThreshold = 50;
		while (shouldAddMod(random.nextInt(100), addModThreshold) && !mods.isEmpty()) {
			Class<? extends Weapon> modClass = mods.remove(random.nextInt(mods.size()));
			weapon = modClass.getConstructor(Weapon.class).newInstance(weapon);
			addModThreshold += 20;
		}

		return weapon;
	}

	private boolean shouldAddMod(int random, int threshold) {
		return random > threshold;
	}

	public Combatent generateCombatent() throws InstantiationException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		if (combatents.size() == 0) {
			throw new RuntimeException("Run out of combatents!");
		}

		Combatent combatent = combatents.remove(random.nextInt(combatents.size()));
		combatent.setWeapon(generateWeapon());
		return combatent;
	}

	public int chance() {
		return random.nextInt(100);
	}

	public int chance(int max) {
		return random.nextInt(max);
	}

}
