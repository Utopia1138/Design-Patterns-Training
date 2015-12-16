package chapter2;

import static org.junit.Assert.assertEquals;
import static subjects.LunchtimeRun.Distance.FIVEKM;
import static subjects.LunchtimeRun.Distance.HALFMARATHON;
import static subjects.LunchtimeRun.Distance.TENKM;
import observers.Ram;
import observers.Sdt;

import org.junit.Test;

import subjects.LunchtimeRun;

public class LunchtimeRunTest extends StdOutCapturingTestClass {

	@Test
	public void testNoObservers() {
		LunchtimeRun r = new LunchtimeRun();
		r.planRun(FIVEKM);
		assertEquals("", testStream.toString());
	}

	@Test
	public void testNoRunSdtObserving() {

		LunchtimeRun r = new LunchtimeRun();
		Sdt s = new Sdt();
		r.addObserver(s);
		r.cancelRun();

		assertEquals("[sdt] Not planning to go running\r\n",
				testStream.toString());

	}

	@Test
	public void test5kSdtObserving() {

		LunchtimeRun r = new LunchtimeRun();
		Sdt s = new Sdt();
		r.addObserver(s);
		r.planRun(FIVEKM);

		assertEquals("[sdt] Commit to 5K\r\n", testStream.toString());

	}

	@Test
	public void test10kSdtObserving() {

		LunchtimeRun r = new LunchtimeRun();
		Sdt s = new Sdt();
		r.addObserver(s);
		r.planRun(TENKM);

		assertEquals("[sdt] Commit to 10K, work slightly longer hours\r\n",
				testStream.toString());

	}

	@Test
	public void testHalfSdtObserving() {

		LunchtimeRun r = new LunchtimeRun();
		Sdt s = new Sdt();
		r.addObserver(s);
		r.planRun(HALFMARATHON);

		assertEquals("[sdt] Commit to half marathon, plead with wife (CBA building that logic in!)\r\n",
				testStream.toString());

	}

	@Test
	public void testHalfRamObserving() {
		LunchtimeRun r = new LunchtimeRun();
		Ram ram = new Ram();
		r.addObserver(ram);
		r.planRun(HALFMARATHON);

		assertEquals("[ram] Not planning to go running\r\n", testStream.toString());
	}
	
	@Test
	public void test5kBothSdtAndRamObserving() {
		LunchtimeRun r = new LunchtimeRun();
		Ram ram = new Ram();
		r.addObserver(ram);
		Sdt s = new Sdt();
		r.addObserver(s);
		r.planRun(FIVEKM);

		assertEquals(	"[sdt] Commit to 5K\r\n"
						+ "[ram] Commit to 5K\r\n",
				testStream.toString());
	}

	@Test
	public void testHalfBothSdtAndRamObserving() {
		LunchtimeRun r = new LunchtimeRun();
		Ram ram = new Ram();
		r.addObserver(ram);
		Sdt s = new Sdt();
		r.addObserver(s);
		r.planRun(HALFMARATHON);

		assertEquals( 	"[sdt] Commit to half marathon, plead with wife (CBA building that logic in!)\r\n"
						+ "[ram] Not planning to go running\r\n",
				testStream.toString());
	}

}
