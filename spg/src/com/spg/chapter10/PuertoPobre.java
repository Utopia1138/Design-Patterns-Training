
package com.spg.chapter10;

import java.util.List;

import com.spg.chapter10.state.GameState;
import com.spg.chapter10.state.StateFactory;
import com.spg.chapter10.state.State;

public class PuertoPobre {

	private GameState	state;

	private int	plantationReserve;
	private int	factoryReserve;
	private int	populationReserve;
	private int	scoreReserve;

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

	public PuertoPobre() {
		this.plantationReserve = 40;
		this.factoryReserve = 20;
		this.populationReserve = 70;
		this.scoreReserve = 100;
	}

	public boolean gameOn() {
		if ( populationReserve < 1 || scoreReserve < 1 ) {
			return false;
		}

		return true;
	}

	public void playTurn( List<Player> players, int activePlayer ) {
		System.out.println( "New Turn Starts!" );
		
		int pos = activePlayer;
		
		setState( StateFactory.getState( players.get( pos ).selectState() ) );

		state.activePlayerAction( players.get( pos ), this );
		if ( pos == players.size() - 1 ) {
			pos = 0;
		}
		else {
			pos++;
		}

		do {
			state.inactivePlayerAction( players.get( pos ), this );

			if ( pos == players.size() - 1 ) {
				pos = 0;
			}
			else {
				pos++;
			}
		}
		while ( pos != activePlayer );
	}

}
