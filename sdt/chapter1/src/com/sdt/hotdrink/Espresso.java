package com.sdt.hotdrink;

import java.util.ArrayList;

import com.sdt.hotdrink.ingredient.CoffeePod;
import com.sdt.hotdrink.ingredient.HotWater;
import com.sdt.hotdrink.ingredient.Ingredient;

public class Espresso extends HotDrinkRecipeImpl {

	public Espresso() {
		ArrayList<Ingredient> ingredients = new ArrayList<>();
		ingredients.add(new HotWater(88));
		ingredients.add(new CoffeePod(CoffeePod.Flavour.ESPRESSO));
		setIngredients(ingredients);
	}

}
