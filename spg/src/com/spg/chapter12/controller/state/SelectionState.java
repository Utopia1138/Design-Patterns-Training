package com.spg.chapter12.controller.state;

import com.spg.chapter12.model.Player;
import com.spg.chapter12.controller.PuertoPobre;


public class SelectionState implements GameState {

	@Override
	public void activePlayerAction( Player player, PuertoPobre gameBoard ) {
		System.out.println( player.getName() + "is making their selection" );

	}

	@Override
	public void inactivePlayerAction( Player player, PuertoPobre gameBoard ) {
		System.out.println( "Inactive players do nothing in this state" );
	}

	@Override
	public void resolution( PuertoPobre gameBoard ) {
		// This isn't even a real state...
		
	}

}
