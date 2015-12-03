/**
 * 
 */
package org.mssw.strategy.cards;

import java.util.LinkedList;

/**
 * @author e047027
 *
 */
public class Cards {

	LinkedList<Card> cards;

	public Card drawCard() {
		return cards.pop();
	}

	public void returnCard(Card card) {
		cards.push(card);
	}

}
