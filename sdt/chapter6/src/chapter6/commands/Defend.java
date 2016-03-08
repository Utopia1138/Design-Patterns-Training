package chapter6.commands;

import chapter6.Army;
import chapter6.Location;

public class Defend implements Command {

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
		army.setStrength(3); // Defensive bonuses ayz ayz
		if(location.getOwnedBy() == army.getCountry()){
			army.setLocation(location);
		} else {
			// You lose your move, sucker!
		}
	}
	
	@Override
	public String toString(){
		return this.getClass().getSimpleName();
	}

}
