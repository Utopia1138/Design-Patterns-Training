
package com.spg.chapter12.controller.state;

import com.spg.chapter12.model.Player;
import com.spg.chapter12.controller.PuertoPobre;

public interface GameState {

	public void activePlayerAction( Player player, PuertoPobre gameBoard );

	public void inactivePlayerAction( Player player, PuertoPobre gameBoard );
	
	public void resolution( PuertoPobre gameBoard );

}
