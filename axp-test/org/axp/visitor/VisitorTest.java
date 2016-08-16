package org.axp.visitor;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.HashMap;

import org.axp.composite.Category;
import org.axp.composite.Diet;
import org.axp.composite.Organism;
import org.axp.composite.OrganismTree;
import org.junit.Before;
import org.junit.Test;

/**
 * Test the {@link OrganismDietCollector} class, showing the power of the visitor pattern.
 */
public class VisitorTest {
	protected static OrganismTree organisms;
	
	/**
	 * Biologists may note that this tree does not contain a full set of biological ranks.
	 * Oh well, it's just for testing.
	 */
	@Before
	public void createOrganisms() {
		VisitorTest.organisms =
			new Category( "Organism" ).add(
				new Category( "Animal" ).add(
					new Category( "Mammal" ).add(
						new Category( "Feline" ).add(
							new Organism( "cat" )
								.setAvgWeight( 4.0f )
								.setDiet( Diet.CARNIVORE ),
							new Organism( "lion" )
								.setAvgWeight( 160.0f )
								.setDiet( Diet.CARNIVORE )
						),
						new Category( "Rodent" ).add(
							new Organism( "mouse" )
								.setAvgWeight( 0.018f )
								.setDiet( Diet.HERBIVORE )
						)
					),
					new Category( "Bird" ).add(
						new Category( "Waterfowl" ).add(
							new Organism( "duck" )
								.setAvgWeight( 1.15f )
								.setDiet( Diet.OMNIVORE )
						)
					),
					new Category( "Fish" ).add(
						new Category( "Carp" ).add(
							new Organism( "goldfish" )
								.setDiet( Diet.OMNIVORE )
						)
					)
					,
					new Category( "Arthropod" ).add(
						new Category( "Spider" ).add(
							new Organism( "cellar spider" )
								.setAvgWeight( 0.001f )
								.setDiet( Diet.CARNIVORE )
						)
					)
				)
			);
	}
	
	@Test
	public void testFind() {
		HashMap<Diet,Integer> diets = organisms.stream().collect( new OrganismDietCollector() );
		assertNotNull( "Diets map", diets );
		assertEquals( "Number of distinct diets", 3, diets.size() );
		assertEquals( "Number of carnivores", new Integer( 3 ), diets.get( Diet.CARNIVORE ) );
		assertEquals( "Number of herbivores", new Integer( 1 ), diets.get( Diet.HERBIVORE ) );
		assertEquals( "Number of omnivores", new Integer( 2 ), diets.get( Diet.OMNIVORE ) );
	}
}
