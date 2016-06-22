
package com.spg.chapter10;

import com.spg.chapter10.state.State;

public class Player {

	private String name;

	private int	money;
	private int	plantations;
	private int	factories;
	private int	population;
	private int	stockpile;
	private int	score;

	public Player( String name ) {
		this.name = name;
		this.money = 10;
		this.plantations = 0;
		this.factories = 0;
		this.population = 0;
		this.stockpile = 0;
		this.score = 0;
	}

	public int getMoney() {
		return money;
	}

	public void setMoney( int money ) {
		this.money = money;
	}

	public void changeMoney( int amount ) {
		this.money += amount;
	}

	public int getPlantations() {
		return plantations;
	}

	public void setPlantations( int plantations ) {
		this.plantations = plantations;
	}

	public void changePlantations( int amount ) {
		this.plantations += amount;
	}

	public int getFactories() {
		return factories;
	}

	public void setFactories( int factories ) {
		this.factories = factories;
	}

	public void changeFactories( int amount ) {
		this.factories += amount;
	}

	public int getPopulation() {
		return population;
	}

	public void setPopulation( int population ) {
		this.population = population;
	}

	public void changePopulation( int amount ) {
		this.population += amount;
	}

	public int getStockpile() {
		return stockpile;
	}

	public void setStockpile( int stockpile ) {
		this.stockpile = stockpile;
	}

	public void changeStockpile( int amount ) {
		this.stockpile += amount;
	}

	public int getScore() {
		return score;
	}

	public void setScore( int score ) {
		this.score = score;
	}

	public void changeScore( int amount ) {
		this.score += amount;
	}

	public String getName() {
		return name;
	}

	public State selectState() {
		// Player selects next state based on RNG. If we wanted to implement some sort of AI, this is the best place to start.
		
		State[] states = State.values();

		int selectionValue = 10;
		
		while ( selectionValue > states.length - 1 ) {
			selectionValue = (int) Math.ceil( Math.random() * 10 );
		}
		
		return states[selectionValue];

	}

}
