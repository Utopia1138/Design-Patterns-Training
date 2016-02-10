package chapter5;

public class TwentyOne {

	// Instantiated on startup, rather than lazy-loaded
	private static TwentyOne twentyOne = new TwentyOne();

	private int counter = 0;

	private TwentyOne() {
	}

	public static TwentyOne getInstance() {
		return twentyOne;
	}

	public synchronized void incrementCounter(int incrementAmount) {
		counter += incrementAmount;
		// If counter reaches 21, reset to zero
		if (counter >= 21) {
			System.out.println("Reached 21, resetting to 0");
			counter = 0;
		}
	}

	// Do getters need to be synchronized? I can't remember ... maybe I need to
	// set the class variable as volatile? For discussion!
	public synchronized int getCounter() {
		return counter;
	}

}
