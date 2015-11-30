package com.sdt.hotdrink;

import java.util.ArrayList;

import com.sdt.hotdrink.ingredient.HotWater;
import com.sdt.hotdrink.ingredient.Ingredient;
import com.sdt.hotdrink.ingredient.Teabag;

public class SandysTea extends HotDrinkRecipeImpl {

	public enum DaySoFar {
		GOOD, BAD, TERRIBLE
	};

	public SandysTea(DaySoFar d) {
		ArrayList<Ingredient> ingredients = new ArrayList<>();
		ingredients.add(new HotWater(75));

		ingredients.add(new Teabag(Teabag.Flavour.TETLEY,
				calculateDiffuseTime(d)));
		setIngredients(ingredients);
	}

	private int calculateDiffuseTime(DaySoFar d) {
		int diffuseTimeSecs;
		if (d == DaySoFar.GOOD) {
			diffuseTimeSecs = 30;
		} else if (d == DaySoFar.BAD) {
			diffuseTimeSecs = 15;
		} else if (d == DaySoFar.TERRIBLE) {
			diffuseTimeSecs = 0;
		} else {
			diffuseTimeSecs = 30;
		}
		return diffuseTimeSecs;
	}

}
