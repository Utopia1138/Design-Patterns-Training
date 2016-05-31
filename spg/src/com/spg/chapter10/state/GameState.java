
package com.spg.chapter10.state;

import com.spg.chapter10.Player;
import com.spg.chapter10.PuertoPobre;

public interface GameState {

	public void activePlayerAction( Player player, PuertoPobre gameBoard );

	public void inactivePlayerAction( Player player, PuertoPobre gameBoard );

}
