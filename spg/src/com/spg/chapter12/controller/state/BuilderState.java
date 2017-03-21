package com.spg.chapter12.controller.state;

import com.spg.chapter12.model.Player;
import com.spg.chapter12.controller.PuertoPobre;


public class BuilderState implements GameState {

	@Override
	public void activePlayerAction( Player player, PuertoPobre gameBoard ) {
		if ( player.getMoney() >= 2 ) {
			player.changeFactories( gameBoard.takeFromFactoryReserve( 1 ) );
			player.changeMoney( -2 );
			System.out.println( player.getName() + " builds a building on the cheap" );
		}
		else {
			System.out.println( player.getName() + " does not build a building" );
		}
	}

	@Override
	public void inactivePlayerAction( Player player, PuertoPobre gameBoard ) {
		if ( player.getMoney() >= 3 ) {
			player.changeFactories( gameBoard.takeFromFactoryReserve( 1 ) );
			player.changeMoney( -3 );
			System.out.println( player.getName() + " builds a building" );
		}
		else {
			System.out.println( player.getName() + " does not build a building" );
		}
	}

	@Override
	public void resolution( PuertoPobre gameBoard ) {
		// Nothing to do
	}

}
