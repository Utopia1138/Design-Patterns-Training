package observers;

import static subjects.Cake.Flavour.UNDETERMINED;

import java.util.Observable;
import java.util.Observer;

import subjects.Cake;
import subjects.LunchtimeRun;

public class Sdt implements Observer {

	private boolean goingRunning;
	private int cakePortionsToday;

	@Override
	public void update(Observable o, Object arg) {
		// Using "pull" version of Observer, so ignoring args

		// Is there a better way to do this, when you have multiple observers?
		// I figured it might be a bit neater to have handler methods?
		if (o.getClass().equals(Cake.class)) {
			handleCake((Cake) o);
			return;
		}

		if (o.getClass().equals(LunchtimeRun.class)) {
			handleRun((LunchtimeRun) o);
			return;
		}
	}

	public void handleCake(Cake c) {
		
		// Note that I haven't built any running specific checks in here, mainly because I CBA!
		// The logic in the running section is therefore brittle, as there is a race condition
		// (Eat cake, then commit to a run = no run. Commit to a run then eat cake = run!)
		
		boolean cakeObtained = false;

		if (c.isCakeInKitchen() && !goingRunning) {
			System.out.println("[sdt] Stop work immediately");
			System.out.println("[sdt] Run to kitchen");
			if (c.getFlavour() != UNDETERMINED) {
				System.out.println("[sdt] Take cake");
				cakeObtained = true;
			}
			System.out.println("[sdt] Make tea");
			System.out.println("[sdt] Return to desk");
			System.out.println("[sdt] Drink tea");

			if (cakeObtained) {
				System.out.println("[sdt] Eat " + c.getFlavour().toString().toLowerCase() + " cake");
				cakePortionsToday++;
			}
			System.out.println("[sdt] Continue working");
		}
	}

	public void handleRun(LunchtimeRun r) {
		if (r.isRunPlanned()) {
			switch (r.getDistance()) {
			case FIVEKM:
				System.out.println("[sdt] Commit to 5K");
				goingRunning = true;
				break;
			case TENKM:
				if (cakePortionsToday < 2) {
					System.out
							.println("[sdt] Commit to 10K, work slightly longer hours");
					goingRunning = true;
					break;
				}
			case HALFMARATHON:
				if (cakePortionsToday == 0) {
					System.out
							.println("[sdt] Commit to half marathon, plead with wife (CBA building that logic in!)");
					goingRunning = true;
					break;
				}
			default:
				System.out.println("[sdt] Not planning to go running");
				goingRunning = false;
			}

		} else {
			System.out.println("[sdt] Not planning to go running");
			goingRunning = false;
		}
	}

}
