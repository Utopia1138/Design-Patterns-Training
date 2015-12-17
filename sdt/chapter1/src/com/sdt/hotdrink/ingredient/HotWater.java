package com.sdt.hotdrink.ingredient;

public class HotWater implements Ingredient {

	private int waterTempCelsius;

	public HotWater(int waterTempCelsius) {
		this.waterTempCelsius = waterTempCelsius;
	}

	@Override
	public void use() {
		System.out.println("Heating water to " + waterTempCelsius + " celsius");
	}

}
