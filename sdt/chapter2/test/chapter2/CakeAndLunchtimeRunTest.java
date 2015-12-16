package chapter2;

import static org.junit.Assert.assertEquals;
import static subjects.Cake.Flavour.CHOCOLATE;
import static subjects.Cake.Flavour.LEMON;
import static subjects.LunchtimeRun.Distance.FIVEKM;
import static subjects.LunchtimeRun.Distance.HALFMARATHON;
import static subjects.LunchtimeRun.Distance.TENKM;
import observers.Sdt;

import org.junit.Test;

import subjects.Cake;
import subjects.LunchtimeRun;

public class CakeAndLunchtimeRunTest extends StdOutCapturingTestClass {

	@Test
	public void test5kRunLotsOfCake() {
		Cake c = new Cake();
		LunchtimeRun r = new LunchtimeRun();
		
		Sdt s = new Sdt();
		c.addObserver(s);
		r.addObserver(s);
		
		// There is tons of shit that would be captured, lets ignore most of it!
		super.restoreOriginalStdOut(); 
		
		c.setCakeInKitchen(LEMON);
		c.setCakeInKitchen(CHOCOLATE);
		
		super.setupStdOutCapture();
		
		r.planRun(FIVEKM);
	
		assertEquals("[sdt] Commit to 5K\r\n", testStream.toString());
	}
	
	@Test
	public void test10kRunLotsOfCake() {
		Cake c = new Cake();
		LunchtimeRun r = new LunchtimeRun();
		
		Sdt s = new Sdt();
		c.addObserver(s);
		r.addObserver(s);
		
		// There is tons of shit that would be captured, lets ignore most of it!
		super.restoreOriginalStdOut(); 
		
		c.setCakeInKitchen(LEMON);
		c.setCakeInKitchen(CHOCOLATE);
		
		super.setupStdOutCapture();
		
		r.planRun(TENKM);
	
		assertEquals("[sdt] Not planning to go running\r\n", testStream.toString());
	}
	
	@Test
	public void testHalfOneCakePortion() {
		Cake c = new Cake();
		LunchtimeRun r = new LunchtimeRun();
		
		Sdt s = new Sdt();
		c.addObserver(s);
		r.addObserver(s);
		
		// There is tons of shit that would be captured, lets ignore most of it!
		super.restoreOriginalStdOut(); 
		
		c.setCakeInKitchen(CHOCOLATE);
		
		super.setupStdOutCapture();
		
		r.planRun(HALFMARATHON);
	
		assertEquals("[sdt] Not planning to go running\r\n", testStream.toString());
	}
	
	@Test
	public void testHalfOneCakePortionChangeTo10k() {
		Cake c = new Cake();
		LunchtimeRun r = new LunchtimeRun();
		
		Sdt s = new Sdt();
		c.addObserver(s);
		r.addObserver(s);
		
		// There is tons of shit that would be captured, lets ignore most of it!
		super.restoreOriginalStdOut(); 
		
		c.setCakeInKitchen(CHOCOLATE);
		
		super.setupStdOutCapture();
		
		r.planRun(HALFMARATHON);
		r.planRun(TENKM);

		assertEquals("[sdt] Not planning to go running\r\n"
				+ "[sdt] Commit to 10K, work slightly longer hours\r\n",
				testStream.toString());
	}
	
}
