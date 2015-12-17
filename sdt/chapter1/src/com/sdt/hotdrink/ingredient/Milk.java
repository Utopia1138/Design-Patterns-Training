package com.sdt.hotdrink.ingredient;

public class Milk implements Ingredient {

	private int ml;

	public Milk(int ml) {
		this.ml = ml;
	}

	@Override
	public void use() {
		System.out.println("Adding " + ml + "ml of milk");
	}

}
