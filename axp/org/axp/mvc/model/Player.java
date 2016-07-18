package org.axp.mvc.model;

import java.util.Random;

public class Player {
	private static final String[] ADJECTIVES = { "Furious", "Happy", "Selfish", "Cantankerous", "Ballistic", "Sneaky", "Stubborn",
		"Eccentric", "Silent", "Bifurcated", "Lovestruck", "Misshapen", "Maligned", "Underprepared", "Sadistic" };
	private static final String[] NOUNS = { "Hippo", "Yak", "Hamster", "Aardvark", "Weasel", "Potato", "Carrot", "Cauliflower",
		"Grapefruit", "Sandwich", "Spaghetti", "Sock", "Trousers", "Kaftan", "Toaster" };
	
	private String name;
	private int score;
	private boolean dead;
	
	public Player() {
		Random random = new Random();
		name = ADJECTIVES[ random.nextInt( ADJECTIVES.length ) ] + ' ' + NOUNS[ random.nextInt( NOUNS.length ) ];
	}
	
	public String getName() {
		return name;
	}
	
	public int getScore() {
		return score;
	}
	
	public void incrementScore() {
		score++;
	}
	
	public void markDead() {
		dead = true;
	}
	
	public boolean isDead() {
		return dead;
	}
	
	@Override
	public boolean equals( Object other ) {
		if ( !( other instanceof Player ) ) return false;
		return ((Player) other).getName().equals( name );
	}
}
