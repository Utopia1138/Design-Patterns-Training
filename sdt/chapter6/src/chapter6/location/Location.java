package chapter6.location;

import java.util.ArrayList;
import java.util.List;

import chapter6.Army;
import chapter6.Country;

public class Location {
	private String name;
	private Country ownedBy;
	private List<Army> defendingForces = new ArrayList<Army>();

	public List<Army> getDefendingForces() {
		return defendingForces;
	}

	public synchronized void setDefendingForces(List<Army> defendingForces) {
		this.defendingForces = defendingForces;
	}

	public Country getOwnedBy() {
		return ownedBy;
	}

	public synchronized void setOwnedBy(Country ownedBy) {
		this.ownedBy = ownedBy;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
