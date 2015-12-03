/**
 * 
 */
package org.mssw.strategy.square.behaviour;

import org.mssw.strategy.player.Player;

/**
 * @author e047027
 *
 */
public interface BuyBehaviour {

	public int getPrice();

	public void setPrice(int price);

	public void setMortgage(int mortgageValue);

	public int mortgage();

	public int unMortgage();

	public int buy();

	public Player getOwner();

	public void setOwner(Player owner);

}
