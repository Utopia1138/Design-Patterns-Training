package com.spg.chapter12.controller.state;

import com.spg.chapter12.model.Player;
import com.spg.chapter12.controller.PuertoPobre;

public class ProspectorState implements GameState {

	@Override
	public void activePlayerAction( Player player, PuertoPobre gameBoard ) {
		System.out.println( player.getName() + " got cash" );
		player.changeMoney( 2 );
	}

	@Override
	public void inactivePlayerAction( Player player, PuertoPobre gameBoard ) {
		System.out.println( "Inactive players do nothing during this state" );
	}

	@Override
	public void resolution( PuertoPobre gameBoard ) {
		// Do nothing
		
	}

}
