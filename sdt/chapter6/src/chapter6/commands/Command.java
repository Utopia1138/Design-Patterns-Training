package chapter6.commands;

import chapter6.Army;
import chapter6.Location;

public interface Command {

	public abstract void execute();

	public abstract void setLocation(Location location);

	public abstract Location getLocation();

	public abstract void setArmy(Army army);

	public abstract Army getArmy();
	
	public abstract String toString();
}
