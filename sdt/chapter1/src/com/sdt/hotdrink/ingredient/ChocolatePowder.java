package com.sdt.hotdrink.ingredient;

public class ChocolatePowder implements Ingredient {

	private int grams;

	public ChocolatePowder(int grams) {
		this.grams = grams;
	}

	@Override
	public void use() {
		System.out.println("Adding " + grams + "g of chocolate powder");
	}

}
