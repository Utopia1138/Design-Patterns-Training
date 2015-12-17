package observers;

import java.util.Observable;
import java.util.Observer;

import subjects.LunchtimeRun;

public class Ram implements Observer {

	public void update(Observable o, Object arg) {
		// Using "pull" version of Observer, so ignoring args

		// @ram is only interested in running
		if (o.getClass().equals(LunchtimeRun.class)) {
			handleRun((LunchtimeRun) o);
			return;
		}
	}

	private void handleRun(LunchtimeRun r) {
		if (r.isRunPlanned()) {
			switch (r.getDistance()) {
			// @ram auto accepts all runs <= 10K
			case FIVEKM:
				System.out.println("[ram] Commit to 5K");
				break;
			case TENKM:
				System.out.println("[ram] Commit to 10K");
				break;
			case HALFMARATHON: // @ram never runs a half marathon on a weekday

			default:
				System.out.println("[ram] Not planning to go running");
			}
		} else {
			System.out.println("[ram] Not planning to go running");
		}
	}

}
