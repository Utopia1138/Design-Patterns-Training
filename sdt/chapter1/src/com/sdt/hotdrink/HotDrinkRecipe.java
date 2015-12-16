package com.sdt.hotdrink;

import java.util.List;

import com.sdt.hotdrink.ingredient.Ingredient;

public interface HotDrinkRecipe {

	/**
	 * Set the ingredients that are involved in this hot drink
	 * 
	 * @param ingredients
	 */
	public void setIngredients(List<Ingredient> ingredients);

	/**
	 * Get the ingredients that this hot drink contains
	 * 
	 * @return ingredients
	 */
	public List<Ingredient> getIngredients();

	/**
	 * Make the hot drink ... (in the context of the office)
	 */
	public abstract void make();

}
