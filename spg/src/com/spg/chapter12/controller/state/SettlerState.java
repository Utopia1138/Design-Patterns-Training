package com.spg.chapter12.controller.state;

import com.spg.chapter12.model.Player;
import com.spg.chapter12.controller.PuertoPobre;


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

	@Override
	public void resolution( PuertoPobre gameBoard ) {
		// Here is where we would sort out the 'face up' plantations if we had any		
	}

}
