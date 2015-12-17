package com.clearscene.Strategy.Atributes.Heating;

public class OilFired implements Heating {

	@Override
	public void HeatingOn() {
		System.out.println("Lighting the boiler");
		System.out.println("Heating is ON");
	}

	@Override
	public void HeatingOff() {
		System.out.println("Heating is OFF");
	}

	@Override
	public String HeatingType() {
		return "OilFired";
	}
}
