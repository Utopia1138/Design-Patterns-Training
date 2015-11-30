package com.sdt.hotdrink;

import java.util.ArrayList;

import com.sdt.hotdrink.ingredient.ChocolatePowder;
import com.sdt.hotdrink.ingredient.CoffeeGranules;
import com.sdt.hotdrink.ingredient.HotWater;
import com.sdt.hotdrink.ingredient.Ingredient;
import com.sdt.hotdrink.ingredient.Milk;

public class Mocha extends HotDrinkRecipeImpl {

	public Mocha(){
		ArrayList<Ingredient> ingredients = new ArrayList<>();
		ingredients.add( new CoffeeGranules(1));
		ingredients.add( new ChocolatePowder(50));
		ingredients.add( new HotWater( 80 ) );
		ingredients.add( new Milk( 50 ));
		setIngredients(ingredients);
	}
	
}
