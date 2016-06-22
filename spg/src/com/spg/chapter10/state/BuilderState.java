package com.spg.chapter10.state;

import com.spg.chapter10.Player;
import com.spg.chapter10.PuertoPobre;


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
		System.out.println( player.getName() + " builds a building" );
		if ( player.getMoney() >= 3 ) {
			player.changeFactories( gameBoard.takeFromFactoryReserve( 1 ) );
			player.changeMoney( -3 );
			System.out.println( player.getName() + " builds a building" );
		}
		else {
			System.out.println( player.getName() + " does not build a building" );
		}
	}

}
