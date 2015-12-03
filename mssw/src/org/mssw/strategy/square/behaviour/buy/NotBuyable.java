/**
 * 
 */
package org.mssw.strategy.square.behaviour.buy;

import org.mssw.strategy.player.Player;
import org.mssw.strategy.square.behaviour.BuyBehaviour;

/**
 * @author e047027
 *
 */
public class NotBuyable implements BuyBehaviour {

	@Override
	public int getPrice() {
		System.out.println("I'm not buyable!");
		return 0;
	}

	@Override
	public void setPrice(int price) {
		System.out.println("I'm not buyable!");

	}

	@Override
	public void setMortgage(int mortgageValue) {
		System.out.println("I'm not buyable!");

	}

	@Override
	public int mortgage() {
		System.out.println("I'm not buyable!");
		return 0;
	}

	@Override
	public int unMortgage() {
		System.out.println("I'm not buyable!");
		return 0;
	}

	@Override
	public int buy() {
		System.out.println("I'm not buyable!");
		return 0;

	}

	@Override
	public Player getOwner() {
		System.out.println("I'm not buyable!");
		return null;
	}

	@Override
	public void setOwner(Player owner) {
		System.out.println("I'm not buyable!");

	}

}
