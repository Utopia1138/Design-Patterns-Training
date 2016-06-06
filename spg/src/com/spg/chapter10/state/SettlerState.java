package com.spg.chapter10.state;

import com.spg.chapter10.Player;
import com.spg.chapter10.PuertoPobre;


public class SettlerState implements GameState {

	@Override
	public void activePlayerAction( Player player, PuertoPobre gameBoard ) {
		System.out.println( player.getName() + " would have the option of picking a quarry, but not in this version so they just get a plantation" );
		player.changePlantations( gameBoard.takeFromPlantationReserve( 1 ) );
	}

	@Override
	public void inactivePlayerAction( Player player, PuertoPobre gameBoard ) {
		System.out.println( player.getName() + " picks a plantation" );
		player.changePlantations( gameBoard.takeFromPlantationReserve( 1 ) );
	}

}
