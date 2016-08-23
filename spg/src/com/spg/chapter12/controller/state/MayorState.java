package com.spg.chapter12.controller.state;

import com.spg.chapter12.model.Player;
import com.spg.chapter12.controller.PuertoPobre;


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

	@Override
	public void resolution( PuertoPobre gameBoard ) {
		// Could put game over condition here?
		
	}

}
