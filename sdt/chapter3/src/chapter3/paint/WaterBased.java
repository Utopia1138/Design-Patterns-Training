package chapter3.paint;

public class WaterBased extends Paint {

	public WaterBased(Paint wrappedObject) {
		super.setWrappedObject(wrappedObject);
	}

	// A noargs constructor is also fine, for the starting object!
	public WaterBased() {	}
	
	@Override
	public double getOpacity() {
		return 0.4; // Covers terribly
	}

}
