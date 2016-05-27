package org.axp.composite;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.TreeSet;

import org.junit.Before;
import org.junit.Test;

/**
 * Test the {@link OrganismTree} class, showing the power of the composite pattern.
 */
public class CompositeTest {
	protected static final Comparator<OrganismTree> NAME_COMPARATOR = ( p, o ) -> p.getName().compareTo( o.getName() );
	
	protected static OrganismTree organisms;
	
	/**
	 * Biologists may note that this tree does not contain a full set of biological ranks.
	 * Oh well, it's just for testing.
	 */
	@Before
	public void createOrganisms() {
		CompositeTest.organisms =
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
	
	@SafeVarargs
	protected static <T> Queue<T> queueOf( T...results ) {
		Queue<T> expected = new LinkedList<>();
		expected.addAll( Arrays.asList( results ) );
		return expected;
	}
	
	@Test
	public void testFind() {
		OrganismTree found = organisms.find( "mouse" );
		assertNotNull( "Mouse not found", found );
		assertEquals( "Wrong class", Organism.class, found.getClass() );
		assertEquals( "Mouse name", "mouse", found.getName() );
		
		found = organisms.find( "Spider" );
		assertNotNull( "Spider not found", found );
		assertEquals( "Wrong class", Category.class, found.getClass() );
		assertEquals( "Spider name", "Spider", found.getName() );
		
		found = organisms.find( "Organism" );
		assertEquals( "Organism not found", organisms, found );
		
		found = organisms.find( "sasquatch" );
		assertNull( "Stop Press! Sasquatch found!", found );
	}
	
	@Test public void testStream() {
		// Show first item is the root of the tree
		assertEquals( "Organism", organisms.stream().findFirst().get().getName() );
		
		// Next show a full list of items, sorted by name
		Queue<String> expected = queueOf(
				"Animal", "Arthropod", "Bird", "Carp", "Feline", "Fish", "Mammal", "Organism", "Rodent", "Spider", "Waterfowl",
				"cat", "cellar spider", "duck", "goldfish", "lion", "mouse" );
		
		organisms.stream()
			.sorted( NAME_COMPARATOR )
			.forEach( o -> assertEquals( "Name mismatch", expected.poll(), o.getName() ) );
		
		assertTrue( "Did not find all organisms", expected.isEmpty() );
	}
	
	@Test
	public void testHeavier() {
		Queue<String> expected = queueOf( "cat", "lion" );
		OrganismTree duck = organisms.find( "duck" );
		
		organisms.stream()
			.filter( critter -> critter.heavierThan( duck.getAvgWeight() ) )
			.sorted( NAME_COMPARATOR )
			.forEach( o -> assertEquals( "Name mismatch", expected.poll(), o.getName() ) );
		
		assertTrue( "Did not find all organisms", expected.isEmpty() );
	}
	
	@Test
	public void testLighter() {
		Queue<String> expected = queueOf( "cellar spider", "mouse" );
		OrganismTree duck = organisms.find( "duck" );
		
		organisms.stream()
			.filter( critter -> critter.lighterThan( duck.getAvgWeight() ) )
			.sorted( NAME_COMPARATOR )
			.forEach( o -> assertEquals( "Name mismatch", expected.poll(), o.getName() ) );
	
		assertTrue( "Did not find all organisms", expected.isEmpty() );
	}
	
	@Test
	public void testDiet() {
		OrganismTree mouse = organisms.stream()
				.filter( critter -> critter.getDiet() == Diet.HERBIVORE )
				.findAny()
				.get();
		
		assertEquals( "Name mismatch", "mouse", mouse.getName() );
	}
	
	@Test
	public void testAdd() {
		organisms.find( "Mammal" ).add(
			new Category( "Simian" ).add(
				new Organism( "human" )
					.setAvgWeight( 80f )
					.setDiet( Diet.OMNIVORE )
			),
			new Organism( "platypus" )
				.setAvgWeight( 1.55f )
				.setDiet( Diet.CARNIVORE )
		);
		
		assertEquals( "Name mismatch", "human", organisms.find( "human" ).getName() );
		assertEquals( "Name mismatch", "Simian", organisms.find( "Simian" ).getName() );
		assertEquals( "Name mismatch", "platypus", organisms.find( "platypus" ).getName() );
		
		Queue<String> expected = queueOf( "Simian", "human" );
		organisms.find( "Simian" ).stream().forEach( o -> assertEquals( "Name mismatch", expected.poll(), o.getName() ) );
		assertTrue( "Did not find all simians", expected.isEmpty() );
	}
	
	@Test
	public void testRemoveIf() {
		Queue<String> expectedLeft = queueOf( "cat", "cellar spider", "lion", "mouse" );
		Queue<String> expectedRmv = queueOf( "duck", "goldfish" );
		
		TreeSet<OrganismTree> removed = new TreeSet<>( NAME_COMPARATOR );
		removed.addAll( organisms.removeIf( critter -> critter.getDiet() == Diet.OMNIVORE ) );
		
		organisms.stream()
			.filter( p -> p instanceof Organism )
			.sorted( NAME_COMPARATOR )
			.forEach( o -> assertEquals( "Name mismatch", expectedLeft.poll(), o.getName() ) );
	
		assertTrue( "Did not remove all organisms", expectedLeft.isEmpty() );
		
		for( OrganismTree critter : removed ) {
			assertEquals( "Name mismatch", expectedRmv.poll(), critter.getName() );
		}
		
		assertTrue( "Did not find all organisms", expectedRmv.isEmpty() );
	}
	
	@Test
	public void testRemoveIfCategories() {
		Queue<String> expectedLeft = queueOf( "Animal", "Arthropod", "Bird", "Mammal", "Organism",
				"Rodent", "Spider", "Waterfowl", "cellar spider", "duck", "mouse" );
		
		Queue<String> fishRemoved = queueOf( "Fish", "Carp", "goldfish" );
		Queue<String> felinesRemoved = queueOf( "Feline", "cat", "lion" );
		
		TreeSet<OrganismTree> removed = new TreeSet<>( NAME_COMPARATOR );
		removed.addAll( organisms.removeIf( cat -> cat.getName().startsWith( "F" ) ) );
		
		organisms.stream()
			.sorted( NAME_COMPARATOR )
			.forEach( o -> assertEquals( "Name mismatch", expectedLeft.poll(), o.getName() ) );
		
		assertTrue( "Did not remove all organisms", expectedLeft.isEmpty() );
		
		removed.pollFirst()
			.stream()
			.sorted( NAME_COMPARATOR )
			.forEach( o -> assertEquals( "Name mismatch", felinesRemoved.poll(), o.getName() ) );
		
		removed.pollFirst()
			.stream()
			.forEach( o -> assertEquals( "Name mismatch", fishRemoved.poll(), o.getName() ) );
		
		assertTrue( "Did not find all felines", felinesRemoved.isEmpty() );
		assertTrue( "Did not find all fish", fishRemoved.isEmpty() );
	}
}
