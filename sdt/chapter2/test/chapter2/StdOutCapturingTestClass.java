package chapter2;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;

public class StdOutCapturingTestClass {

	protected PrintStream originalSystemOut;
	protected ByteArrayOutputStream testStream;

	@Before
	public void setupStdOutCapture() {
		testStream = new ByteArrayOutputStream();
		PrintStream testPs = new PrintStream(testStream);
		originalSystemOut = System.out; // Backup!
		System.setOut(testPs);
	}

	@After
	public void restoreOriginalStdOut() {
		System.setOut(originalSystemOut);
	}
	
}
