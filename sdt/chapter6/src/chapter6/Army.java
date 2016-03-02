package chapter6;

public class Army {
	private Country country;
	private int size;
	private int strength = 1;
	private int experience = 1;
	
	private int sizeAtStartOfBattle; // This should be held in the BattleSimulator but I couldn't be bothered.

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
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

	public int getSizeAtStartOfBattle() {
		return sizeAtStartOfBattle;
	}

	public void setSizeAtStartOfBattle(int sizeAtStartOfBattle) {
		this.sizeAtStartOfBattle = sizeAtStartOfBattle;
	}

}
