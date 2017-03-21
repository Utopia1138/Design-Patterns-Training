
package com.spg.chapter12.model;

import java.util.Observable;

import com.spg.chapter12.controller.state.State;

public class Player extends Observable {

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
		updateView();
	}

	public void changeMoney( int amount ) {
		this.money += amount;
		updateView();
	}

	public int getPlantations() {
		return plantations;
	}

	public void setPlantations( int plantations ) {
		this.plantations = plantations;
		updateView();
	}

	public void changePlantations( int amount ) {
		this.plantations += amount;
		updateView();
	}

	public int getFactories() {
		return factories;
	}

	public void setFactories( int factories ) {
		this.factories = factories;
		updateView();
	}

	public void changeFactories( int amount ) {
		this.factories += amount;
		updateView();
	}

	public int getPopulation() {
		return population;
	}

	public void setPopulation( int population ) {
		this.population = population;
		updateView();
	}

	public void changePopulation( int amount ) {
		this.population += amount;
		updateView();
	}

	public int getStockpile() {
		return stockpile;
	}

	public void setStockpile( int stockpile ) {
		this.stockpile = stockpile;
		updateView();
	}

	public void changeStockpile( int amount ) {
		this.stockpile += amount;
	}

	public int getScore() {
		return score;
	}

	public void setScore( int score ) {
		this.score = score;
		updateView();
	}

	public void changeScore( int amount ) {
		this.score += amount;
		updateView();
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
	
	public String displayHand() {
		StringBuilder builder = new StringBuilder();
		builder.append( "Hand for " + getName() + ":\n"  );
		builder.append( "Money : " + getMoney() + "\n" );
		builder.append( "Plantations : " + getPlantations() + "\n" );
		builder.append( "Factories : " + getFactories() + "\n" );
		builder.append( "Population : " + getPopulation() + "\n" );
		builder.append( "Stockpile : " + getStockpile() + "\n" );
		builder.append( "Score : " + getScore() + "\n" );

		return builder.toString();
	}
	
	private void updateView() {
		setChanged();
		notifyObservers();
	}

}
