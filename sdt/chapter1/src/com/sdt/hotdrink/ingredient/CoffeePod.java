package com.sdt.hotdrink.ingredient;

public class CoffeePod implements Ingredient {

	public enum Flavour {
		ESPRESSO, LUNGO, DECAFFEINATO
	};

	private Flavour flavour;

	public CoffeePod(Flavour flavour) {
		this.flavour = flavour;
	}

	@Override
	public void use() {
		System.out.println("Put " + flavour + " coffee pod in machine");
	}

}
