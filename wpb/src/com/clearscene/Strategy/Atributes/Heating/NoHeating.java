package com.clearscene.Strategy.Atributes.Heating;

public class NoHeating implements Heating {

	@Override
	public void HeatingOn() {
		System.out.println("-- Burrr, no heating found --");
	}

	@Override
	public void HeatingOff() {
		System.out.println("-- Burrr, no heating found --");
	}

	@Override
	public String HeatingType() {
		return "no heating";
	}
}
