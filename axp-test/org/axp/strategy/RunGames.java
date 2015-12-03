package org.axp.strategy;

import org.junit.Test;

public class RunGames {

	@Test
	public void testMonopoly() {
		Game monopoly = new Monopoly( "Alice", "Bob", "Carol", "Dave" );
		monopoly.runGame();
	}
	
	@Test
	public void testBananagrams() {
		Game bananagrams = new Bananagrams( "Eve", "Fred", "Gina", "Harry", "Ida" );
		bananagrams.runGame();
	}
	
	@Test
	public void testScrabble() {
		Game scrabble = new Scrabble( "Jack", "Kate", "Larry", "Mary" );
		scrabble.runGame();
	}

}
