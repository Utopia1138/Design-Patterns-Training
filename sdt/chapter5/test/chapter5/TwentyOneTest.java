package chapter5;

import org.junit.Test;

public class TwentyOneTest {

	@Test
	public void vagueTest() {
		TwentyOne instance1 = TwentyOne.getInstance();
		TwentyOne instance2 = TwentyOne.getInstance();

		for (int i = 0; i < 10; i++) {
			System.out.println("i1 incrementing by: " + i);
			instance1.incrementCounter(i);
			System.out.println("i2 current value: " + instance2.getCounter());
			System.out.println("i2 incrementing by: " + i);
			instance2.incrementCounter(i);
			System.out.println("i1 current value: " + instance1.getCounter());			
		}
		
		System.out.println("Bored now, finished ...");
	}

}
