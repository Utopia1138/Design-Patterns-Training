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
public class Property implements BuyBehaviour {
	private int price;
	private int mortgageValue;
	private boolean mortgaged;
	private Player owner;

	@Override
	public int getPrice() {

		return 0;
	}

	@Override
	public void setPrice(int price) {
		this.price = price;

	}

	@Override
	public void setMortgage(int mortgageValue) {
		this.mortgageValue = mortgageValue;

	}

	@Override
	public int mortgage() {
		if (mortgaged) {
			System.out.println("You have already mortgaged this!");
			return 0;
		}

		this.mortgaged = true;
		return mortgageValue;
	}

	@Override
	public int unMortgage() {
		if (!mortgaged) {
			System.out.println("You haven't mortgaged this!");
			return 0;
		}

		this.mortgaged = false;
		return -mortgageValue;
	}

	@Override
	public int buy() {
		return price;

	}

	@Override
	public Player getOwner() {
		return owner;
	}

	@Override
	public void setOwner(Player owner) {
		this.owner = owner;
	}

}
