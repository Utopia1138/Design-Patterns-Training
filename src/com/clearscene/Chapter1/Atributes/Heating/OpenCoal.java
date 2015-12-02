package com.clearscene.Chapter1.Atributes.Heating;

public class OpenCoal implements Heating {

	@Override
	public void HeatingOn() {
		System.out.println("Building a fire");
		System.out.println("Lighting the fire");
		System.out.println("Adding coals");
		System.out.println("Heating is ON");
	}

	@Override
	public void HeatingOff() {
		System.out.println("Waiting for the fire to go out...");
		try { Thread.sleep(5); } catch (Exception e) {}
		System.out.println("Heating is OFF.");
	}

	@Override
	public String HeatingType() {
		 return "OpenCoal";
	}
}
