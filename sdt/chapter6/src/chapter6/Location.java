package chapter6;

public class Location {
	private String name;
	private Country ownedBy;

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

	public String toString() {
		return name + " [" + ownedBy + "]";
	}
}
