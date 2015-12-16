package com.sdt.hotdrink.ingredient;


public class CoffeeGranules implements Ingredient {

	private float teaspoons;
	
	public CoffeeGranules(float teaspoons) {
		this.teaspoons = teaspoons;
	}
	
	@Override
	public void use() {
		System.out.println("Adding " + teaspoons + " teaspoons of coffee granules");

	}

}
