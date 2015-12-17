package com.sdt.hotdrink.ingredient;

public class Teabag implements Ingredient {

	public enum Flavour {
		GREEN, TETLEY
	};

	private Flavour flavour;
	private int diffuseTimeSecs;

	public Teabag(Flavour flavour, int diffuseTimeSecs) {
		this.flavour = flavour;
		this.diffuseTimeSecs = diffuseTimeSecs;
	}

	@Override
	public void use() {
		System.out.println("Adding " + flavour + " teabag");
		System.out.println("Waiting " + diffuseTimeSecs + " seconds");
		System.out.println("Removing teabag");
	}

}
