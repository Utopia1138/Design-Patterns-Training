/**
 * 
 */
package org.mssw.strategy.square.behaviour.pickup;

import org.mssw.strategy.cards.Card;
import org.mssw.strategy.cards.Cards;
import org.mssw.strategy.square.behaviour.PickupBehaviour;

/**
 * @author e047027
 *
 */
public class CommunityChest implements PickupBehaviour {
	private Cards cards;

	@Override
	public Card pickup() {
		// TODO Auto-generated method stub
		return cards.drawCard();
	}

	@Override
	public void setCards(Cards cards) {
		// TODO Auto-generated method stub
		this.cards = cards;

	}
}
