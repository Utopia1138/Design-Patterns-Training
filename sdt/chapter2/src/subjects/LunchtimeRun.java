package subjects;

import java.util.Observable;

public class LunchtimeRun extends Observable {

	public enum Distance { FIVEKM, TENKM, HALFMARATHON };

	private boolean runPlanned;
	private Distance distance;
	
	public Distance getDistance() {
		return distance;
	}

	public void planRun(Distance distance) {
		this.runPlanned = true;
		this.distance = distance;
		setChanged();
		notifyObservers();
	}

	public boolean isRunPlanned() {
		return runPlanned;
	}

	public void cancelRun() {
		this.runPlanned = false;
		setChanged();
		notifyObservers();
	}
	
}
