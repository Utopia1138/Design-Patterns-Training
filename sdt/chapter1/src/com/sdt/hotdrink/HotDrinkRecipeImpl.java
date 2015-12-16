package com.sdt.hotdrink;

import java.util.List;

import com.sdt.hotdrink.ingredient.Ingredient;

public abstract class HotDrinkRecipeImpl implements HotDrinkRecipe {

	private List<Ingredient> ingredients;

	@Override
	public void setIngredients(List<Ingredient> ingredients) {
		this.ingredients = ingredients;
	}

	@Override
	public List<Ingredient> getIngredients() {
		return ingredients;
	}

	@Override
	public void make() {

		for (Ingredient i : ingredients) {
			i.use();
		}

		System.out.println("Ready!");
	}

}
