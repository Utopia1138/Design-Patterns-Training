package com.spg.chapter10.state;

import com.spg.chapter10.Player;
import com.spg.chapter10.PuertoPobre;


public class TraderState implements GameState {

	@Override
	public void activePlayerAction( Player player, PuertoPobre gameBoard ) {
		if ( player.getStockpile() > 0 ) {
			player.changeStockpile( -1 );
			player.changeMoney( 4 );
			System.out.println( player.getName() + " sells off a bit of stockpile for more cash" );
		} 
		else {
			System.out.println( player.getName() + " has nothing to sell" );
		}
	}

	@Override
	public void inactivePlayerAction( Player player, PuertoPobre gameBoard ) {
		if ( player.getStockpile() > 0 ) {
			player.changeStockpile( -1 );
			player.changeMoney( 3 );
			System.out.println( player.getName() + " sells off a bit of stockpile for cash" );
		}
		else {
			System.out.println( player.getName() + " has nothing to sell" );
		}
	}

}
