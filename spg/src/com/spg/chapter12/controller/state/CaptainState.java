package com.spg.chapter12.controller.state;

import com.spg.chapter12.model.CargoShip;
import com.spg.chapter12.model.Player;
import com.spg.chapter12.controller.PuertoPobre;


public class CaptainState implements GameState {

	@Override
	public void activePlayerAction( Player player, PuertoPobre gameBoard ) {
		int loaded = loadStockpile(player, gameBoard);
		System.out.println( player.getName() + " sends their stockpile of " + loaded +  " back to the metropole first" );
		player.changeScore( gameBoard.takeFromScoreReserve( loaded + 1 ) );
	}

	@Override
	public void inactivePlayerAction( Player player, PuertoPobre gameBoard ) {
		int loaded = loadStockpile(player, gameBoard);
		System.out.println( player.getName() + " sends their stockpile of " + loaded +  " back to the metropole" );
		player.changeScore( gameBoard.takeFromScoreReserve( loaded ) );
	}

	@Override
	public void resolution( PuertoPobre gameBoard ) {
		// Reset ships
		for( CargoShip ship : gameBoard.getShips() ) {
			ship.setLoad( 0 );
		}
	}
	
	private int loadStockpile( Player player, PuertoPobre gameBoard ) {
		int loaded = 0;
		
		for ( CargoShip ship : gameBoard.getShips() ) {
			if ( player.getStockpile() == 0 ) {
				break;
			}
			
			if ( ship.getLoad() == ship.getSize() ) {
				continue;
			}
			
			// Each player can only load stockpile up on one ship, they can only have one left over
			loaded = ship.addLoad( player.getStockpile() );
			player.setStockpile( player.getStockpile() - loaded == 0 ? 0 : 1 );
			break;
		}
		return loaded;
	}

}
