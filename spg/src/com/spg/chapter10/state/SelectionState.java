package com.spg.chapter10.state;

import com.spg.chapter10.Player;
import com.spg.chapter10.PuertoPobre;


public class SelectionState implements GameState {

	@Override
	public void activePlayerAction( Player player, PuertoPobre gameBoard ) {
		System.out.println( player.getName() + "is making their selection" );

	}

	@Override
	public void inactivePlayerAction( Player player, PuertoPobre gameBoard ) {
		System.out.println( "Inactive players do nothing in this state" );
	}

}
