
package com.spg.chapter12.controller;

import java.util.ArrayList;
import java.util.List;

import com.spg.chapter12.controller.state.GameState;
import com.spg.chapter12.controller.state.State;
import com.spg.chapter12.controller.state.StateFactory;
import com.spg.chapter12.model.CargoShip;
import com.spg.chapter12.model.Player;
import com.spg.chapter12.view.Table;

public class PuertoPobre {

	private GameState	state;

	private int	plantationReserve;
	private int	factoryReserve;
	private int	populationReserve;
	private int	scoreReserve;
	
	private List<CargoShip> ships;

	public GameState getState() {
		return state;
	}

	public void setState( GameState state ) {
		this.state = state;
	}

	public int getPlantationReserve() {
		return plantationReserve;
	}

	public int takeFromPlantationReserve( int amount ) {
		int actual = amount;
		plantationReserve -= amount;
		if ( plantationReserve < 0 ) {
			actual = amount + plantationReserve;
			plantationReserve = 0;
		}

		if ( actual < 0 ) {
			return 0;
		}

		return actual;
	}

	public int getFactoryReserve() {
		return factoryReserve;
	}

	public int takeFromFactoryReserve( int amount ) {
		int actual = amount;
		factoryReserve -= amount;
		if ( factoryReserve < 0 ) {
			actual = amount + factoryReserve;
			factoryReserve = 0;
		}

		if ( actual < 0 ) {
			return 0;
		}

		return actual;
	}

	public int getPopulationReserve() {
		return populationReserve;
	}

	public int takeFromPopulationReserve( int amount ) {
		int actual = amount;
		populationReserve -= amount;
		if ( populationReserve < 0 ) {
			actual = amount + populationReserve;
			populationReserve = 0;
		}

		if ( actual < 0 ) {
			return 0;
		}

		return actual;
	}

	public int getScoreReserve() {
		return scoreReserve;
	}

	public int takeFromScoreReserve( int amount ) {
		int actual = amount;
		scoreReserve -= amount;
		if ( scoreReserve < 0 ) {
			actual = amount + scoreReserve;
			scoreReserve = 0;
		}

		if ( actual < 0 ) {
			return 0;
		}

		return actual;
	}
	
	public List<CargoShip> getShips() {
		return ships;
	}

	public PuertoPobre() {
		this.plantationReserve = 40;
		this.factoryReserve = 20;
		this.populationReserve = 70;
		this.scoreReserve = 100;
		
		ships = new ArrayList<>();
		ships.add( new CargoShip( 8 ) );
		ships.add( new CargoShip( 7 ) );
		ships.add( new CargoShip( 5 ) );
		ships.add( new CargoShip( 4 ) );
	}

	public boolean gameOn() {
		if ( populationReserve < 1 || scoreReserve < 1 ) {
			return false;
		}

		return true;
	}

	public void playTurn( List<Table> players, int activePlayer, State selected ) {
		System.out.println( "New Turn Starts!" );
		
		int pos = activePlayer;
		
		setState( StateFactory.getState( selected ) );

		state.activePlayerAction( players.get( pos ).getPlayer(), this );
		if ( pos == players.size() - 1 ) {
			pos = 0;
		}
		else {
			pos++;
		}

		do {
			state.inactivePlayerAction( players.get( pos ).getPlayer(), this );

			if ( pos == players.size() - 1 ) {
				pos = 0;
			}
			else {
				pos++;
			}
		}
		while ( pos != activePlayer );
		
		state.resolution( this );
	}

}
