package chapter6.commands;

import chapter6.Army;
import chapter6.Location;

public class Recouperate implements Command {

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
		army.setStrength(1); // Healing up = weak defense
		
		int newSize = (int) ( (double) army.getSize() * 1.33);
		if(newSize > army.getInitialSize()){
			newSize=army.getInitialSize();
		}
		army.setSize(newSize);
		System.out.println(army);
		
		if(location.getOwnedBy() == army.getCountry()){
			army.setLocation(location);
		} else {
			// Don't move anywhere, sucker!
		}
	}

	@Override
	public String toString() {
		return this.getClass().getSimpleName();
	}
}
