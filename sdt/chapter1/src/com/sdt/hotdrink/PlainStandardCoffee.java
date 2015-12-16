package com.sdt.hotdrink;

import java.util.ArrayList;

import com.sdt.hotdrink.ingredient.CoffeeGranules;
import com.sdt.hotdrink.ingredient.HotWater;
import com.sdt.hotdrink.ingredient.Ingredient;
import com.sdt.hotdrink.ingredient.Milk;

public class PlainStandardCoffee extends HotDrinkRecipeImpl {

	public PlainStandardCoffee() {
		ArrayList<Ingredient> ingredients = new ArrayList<>();
		ingredients.add(new CoffeeGranules(2));
		ingredients.add(new HotWater(85));
		ingredients.add(new Milk(50));
		setIngredients(ingredients);
	}

}
