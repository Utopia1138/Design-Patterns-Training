package chapter3.paint;

public abstract class Paint {

	private Paint wrappedObject;
	
	private int r;
	private int g;
	private int b;

	public void setColour(int r, int g, int b) {
		this.r = r;
		this.g = g;
		this.b = b;
	}
	
	public int getR(){
		return r;
	}
	
	public int getG(){
		return g;
	}
	
	public int getB(){
		return b;
	}

	public abstract double getOpacity();

	public Paint getWrappedObject() {
		return wrappedObject;
	}

	public void setWrappedObject(Paint wrappedObject) {
		this.wrappedObject = wrappedObject;
	}

	@Override
	public String toString() {
		return "Paint [r=" + r + ", g=" + g + ", b=" + b + "]";
	}
}
