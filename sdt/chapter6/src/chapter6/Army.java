package chapter6;

public class Army {
	private Country country;
	private Location location;

	private int initialSize = 0;
	private int size;
	private int strength = 1;
	private int experience = 1;

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
		if (initialSize == 0) {
			this.initialSize = size;
		}
	}

	public int getStrength() {
		return strength;
	}

	public void setStrength(int strength) {
		this.strength = strength;
	}

	public int getExperience() {
		return experience;
	}

	public void setExperience(int experience) {
		this.experience = experience;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public String toString() {

		double percentageOfSize = (((double) size / (double) initialSize) * 100.0);

		return "Army - Country: " + getCountry() + " Size: " + getSize()
				+ " [ " + percentageOfSize + "% ]";
	}

	public int getInitialSize() {
		return initialSize;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public boolean isDead() {
		return (size == 0);
	}

}
