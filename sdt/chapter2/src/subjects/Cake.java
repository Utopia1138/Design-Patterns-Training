package subjects;

import static subjects.Cake.Flavour.UNDETERMINED;

import java.util.Observable;

public class Cake extends Observable {

	public enum Flavour {
		CHOCOLATE, CARROT, LEMON, UNDETERMINED
	};

	private boolean cakeInKitchen;
	private Flavour flavour = UNDETERMINED;

	public Flavour getFlavour() {
		return flavour;
	}

	public void setFlavour(Flavour flavour) {
		this.flavour = flavour;
		setChanged();
		notifyObservers();
	}

	public boolean isCakeInKitchen() {
		return cakeInKitchen;
	}

	public void setNoCakeInKitchen() {
		this.cakeInKitchen = false;
		setChanged();
		notifyObservers();
	}

	public void setCakeInKitchen(Flavour flavour) {
		this.cakeInKitchen = true;
		this.flavour = flavour;
		setChanged();
		notifyObservers();
	}

}
