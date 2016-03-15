package chapter6.commands;

import chapter6.Army;
import chapter6.BattleSimulator;
import chapter6.Location;

public class Attack implements Command {

	private Army army;
	private Location location;
	
	@Override
	public Army getArmy() {
		return army;
	}

	@Override
	public void setArmy(Army army) {
		this.army = army;
	}

	@Override
	public Location getLocation() {
		return location;
	}

	@Override
	public void setLocation(Location location) {
		this.location = location;
	}

	@Override
	public void execute() {
		BattleSimulator bs = new BattleSimulator();
		army.setStrength(2); // Defensive bonuses ayz ayz
		bs.attack(army, location);
	}

	@Override
	public String toString(){
		return this.getClass().getSimpleName();
	}
	
}
