package com.spg.chapter12.controller.state;

import com.spg.chapter12.model.Player;
import com.spg.chapter12.controller.PuertoPobre;


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

	@Override
	public void resolution( PuertoPobre gameBoard ) {
		// If this worked correctly, we'd empty out the clogged trader here
		
	}
}
