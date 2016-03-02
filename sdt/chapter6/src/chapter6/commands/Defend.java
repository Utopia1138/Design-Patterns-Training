package chapter6.commands;

import java.util.List;

import chapter6.Army;
import chapter6.location.Location;

public class Defend implements Command {

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

	@Override
	public void execute() {
		List<Army> defenders = location.getDefendingForces();
		defenders.add(army);
	}

}
