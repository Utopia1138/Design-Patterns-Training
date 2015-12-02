package com.clearscene.Chapter1.Atributes.Heating;

public class GasCentral implements Heating {

	@Override
	public void HeatingOn() {
		System.out.println("Heating is ON");
	}

	@Override
	public void HeatingOff() {
		System.out.println("Heating is OFF");
	}

	@Override
	public String HeatingType() {
		return "GasCentral";
	}
	
}
