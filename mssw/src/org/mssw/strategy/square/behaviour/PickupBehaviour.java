/**
 * 
 */
package org.mssw.strategy.square.behaviour;

import org.mssw.strategy.cards.Card;
import org.mssw.strategy.cards.Cards;

/**
 * @author e047027
 *
 */
public interface PickupBehaviour {

	public Card pickup();

	public void setCards(Cards cards);

}
