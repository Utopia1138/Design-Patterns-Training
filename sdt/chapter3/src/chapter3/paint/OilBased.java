package chapter3.paint;

public class OilBased extends Paint {
	
	public OilBased(Paint objectToBeWrapped) {
		super.setWrappedObject(objectToBeWrapped);
	}

	// A noargs constructor is also fine, for the starting object!
	public OilBased() {	}

	@Override
	public double getOpacity() {
		return 0.8; // Covers pretty well
	}

}
