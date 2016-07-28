package com.spg.chapter12.controller.state;

import com.spg.chapter12.model.Player;
import com.spg.chapter12.controller.PuertoPobre;


public class CraftsmanState implements GameState {

	@Override
	public void activePlayerAction( Player player, PuertoPobre gameBoard ) {
		int production = doHarvest( player );
		
		System.out.println( player.getName() + " harvests " + production + " fields and some more" );
		
		player.changeStockpile( production + 1 );
	}

	@Override
	public void inactivePlayerAction( Player player, PuertoPobre gameBoard ) {
		int production = doHarvest( player );

		System.out.println( player.getName() + " harvests " + production + " fields" );
		
		player.changeStockpile( production );
	}

	private static int doHarvest( Player player ) {
		// Population/2 is the cap on production
		int workingPop = Math.floorDiv( player.getPopulation(), 2 );
		
		// Take lowest of plantations/factories
		int production = 0;
		if ( player.getFactories() < player.getPlantations() ) {
			production = player.getFactories();
		}
		else if ( player.getFactories() > player.getPlantations() ) {
			production = player.getPlantations();
		}
		else {
			production = player.getFactories();
		}
		
		if ( workingPop < production ) {
			production = workingPop;
		}
		
		return production;
	}

	@Override
	public void resolution( PuertoPobre gameBoard ) {
		// Do nothing
		
	}
}
