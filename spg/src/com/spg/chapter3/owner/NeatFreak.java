package com.spg.chapter3.owner;

import com.spg.chapter3.House;
import com.spg.chapter3.PriceImpactor;

/**
 * A really tidy owner makes things look neat for the valuators.
 * 
 * @author E043175
 *
 */
public class NeatFreak extends PriceImpactor {

	public NeatFreak( House pricedHouse ) {
		this.pricedHouse = pricedHouse;
	}
	
	@Override
	public double getPrice() {
		return pricedHouse.getPrice() * 1.1;
	}
	
}
