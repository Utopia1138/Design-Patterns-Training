package chapter6;



public class Main {

	public static void main(String[] args) {
		
		WarSetup w = new WarSetup();
		w.setupLocations();
		w.setupArmies();

		TurnManager tm = new TurnManager();
		tm.start();

	}

}
