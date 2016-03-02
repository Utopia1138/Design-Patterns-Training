package chapter6.commands;

import chapter6.Army;
import chapter6.battle.BattleSimulator;
import chapter6.location.Location;

public class Attack implements Command {

	private Army army;
	private Location location;

	public Army getArmy() {
		return army;
	}

	public void setArmy(Army army) {
		this.army = army;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}
	
	public void execute(){
		BattleSimulator bs = new BattleSimulator();
		bs.attack(army, location);
	}

}
