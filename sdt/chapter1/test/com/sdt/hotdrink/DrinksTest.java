package com.sdt.hotdrink;

import java.util.List;

import org.junit.Test;

import com.sdt.hotdrink.ingredient.CoffeeGranules;
import com.sdt.hotdrink.ingredient.Ingredient;

/**
 * 
 * I couldn't think of a better idea than hot drinks - sorry!
 * 
 * @author Sandy
 */

public class DrinksTest {

	@Test
	public void testEspresso() {
		Espresso e = new Espresso();
		e.make();
		// Not actually testing anything really ... ho hum

		// Output:
		// Heating water to 88 celsius
		// Put ESPRESSO coffee pod in machine
		// Ready!
	}

	@Test
	public void testMocha() {
		Mocha e = new Mocha();
		e.make();
		// Not actually testing anything really ... ho hum

		// Output:
		// Adding 1.0 teaspoons of coffee granules
		// Adding 50g of chocolate powder
		// Heating water to 80 celsius
		// Adding 50ml of milk
		// Ready!
	}

	@Test
	public void testPlainStandardCoffee() {
		PlainStandardCoffee e = new PlainStandardCoffee();
		e.make();
		// Not actually testing anything really ... ho hum

		// Output:
		// Adding 2.0 teaspoons of coffee granules
		// Heating water to 85 celsius
		// Adding 50ml of milk
		// Ready!
	}

	@Test
	public void testSandysTeaGoodDay() {
		SandysTea e = new SandysTea(SandysTea.DaySoFar.GOOD);
		e.make();
		// Not actually testing anything really ... ho hum

		// Output:
		// Heating water to 75 celsius
		// Adding TETLEY teabag
		// Waiting 30 seconds
		// Removing teabag
		// Ready!
	}

	@Test
	public void testSandysTeaTerribleDay() {
		SandysTea e = new SandysTea(SandysTea.DaySoFar.TERRIBLE);
		e.make();
		// Not actually testing anything really ... ho hum

		// Output:
		// Heating water to 75 celsius
		// Adding TETLEY teabag
		// Waiting 0 seconds
		// Removing teabag
		// Ready!
	}

	@Test
	public void testSpikeSandysTeaTerribleDay() {
		SandysTea e = new SandysTea(SandysTea.DaySoFar.TERRIBLE);
		List<Ingredient> updatedIngredients = e.getIngredients();
		updatedIngredients.add(new CoffeeGranules(3));
		e.setIngredients(updatedIngredients);

		e.make();
		// Not actually testing anything really ... ho hum

		// Output:
		// Heating water to 75 celsius
		// Adding TETLEY teabag
		// Waiting 0 seconds
		// Removing teabag
		// Adding 3.0 teaspoons of coffee granules
		// Ready!

	}

}
