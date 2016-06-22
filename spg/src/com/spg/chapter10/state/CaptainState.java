package com.spg.chapter10.state;

import com.spg.chapter10.Player;
import com.spg.chapter10.PuertoPobre;


public class CaptainState implements GameState {

	@Override
	public void activePlayerAction( Player player, PuertoPobre gameBoard ) {
		System.out.println( player.getName() + " sends their stockpile of " + player.getStockpile() +  " back to the metropole first" );
		player.changeScore( gameBoard.takeFromScoreReserve( player.getStockpile() + 1 ) );
		player.setStockpile( 0 );
	}

	@Override
	public void inactivePlayerAction( Player player, PuertoPobre gameBoard ) {
		System.out.println( player.getName() + " sends their stockpile of " + player.getStockpile() +  " back to the metropole" );
		player.changeScore( gameBoard.takeFromScoreReserve( player.getStockpile() ) );
		player.setStockpile( 0 );
	}

}
