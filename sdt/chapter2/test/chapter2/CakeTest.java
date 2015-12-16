package chapter2;

import static org.junit.Assert.assertEquals;
import static subjects.Cake.Flavour.LEMON;
import observers.Ram;
import observers.Sdt;

import org.junit.Test;

import subjects.Cake;

public class CakeTest extends StdOutCapturingTestClass {

	@Test
	public void testNoCake() {
		Cake c = new Cake();
		Sdt s = new Sdt();
		c.addObserver(s);
		c.setNoCakeInKitchen();

		assertEquals("", testStream.toString());
	}
	
	@Test
	public void testLemonCakeInKitchen() {
		Cake c = new Cake();
		Sdt s = new Sdt();
		c.addObserver(s);
		c.setCakeInKitchen(LEMON);

		assertEquals("[sdt] Stop work immediately\r\n" + 
				"[sdt] Run to kitchen\r\n" + 
				"[sdt] Take cake\r\n" + 
				"[sdt] Make tea\r\n" + 
				"[sdt] Return to desk\r\n" + 
				"[sdt] Drink tea\r\n" + 
				"[sdt] Eat lemon cake\r\n" + 
				"[sdt] Continue working\r\n" + 
				"", testStream.toString());
	}
	
	@Test
	public void testLemonCakeInKitchenSdtAndRam() {
		Cake c = new Cake();
		Sdt s = new Sdt();
		Ram r = new Ram();

		c.addObserver(s);
		c.addObserver(r);

		c.setCakeInKitchen(LEMON);

		assertEquals("[sdt] Stop work immediately\r\n" + 
				"[sdt] Run to kitchen\r\n" + 
				"[sdt] Take cake\r\n" + 
				"[sdt] Make tea\r\n" + 
				"[sdt] Return to desk\r\n" + 
				"[sdt] Drink tea\r\n" + 
				"[sdt] Eat lemon cake\r\n" + 
				"[sdt] Continue working\r\n",
				testStream.toString());
	}

}
