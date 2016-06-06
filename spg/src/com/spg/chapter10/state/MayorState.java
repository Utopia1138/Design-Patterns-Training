package com.spg.chapter10.state;

import com.spg.chapter10.Player;
import com.spg.chapter10.PuertoPobre;


public class MayorState implements GameState {

	@Override
	public void activePlayerAction( Player player, PuertoPobre gameBoard ) {
		System.out.println( player.getName() + " adds two population" );
		player.changePopulation( gameBoard.takeFromPopulationReserve( 2 ) );
	}

	@Override
	public void inactivePlayerAction( Player player, PuertoPobre gameBoard ) {
		System.out.println( player.getName() + " adds one population" );
		player.changePopulation( gameBoard.takeFromPopulationReserve( 1 ) );
	}

}
