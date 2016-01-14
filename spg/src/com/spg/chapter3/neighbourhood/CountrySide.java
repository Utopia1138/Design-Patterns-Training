package com.spg.chapter3.neighbourhood;

import com.spg.chapter3.House;
import com.spg.chapter3.PriceImpactor;

/**
 * Nice, and the plants were singing and the birds and sun were almost down from the top of the sky. 
 * 
 * @author E043175
 *
 */
public class CountrySide extends PriceImpactor {

	public CountrySide( House pricedHouse ) {
		this.pricedHouse = pricedHouse;
	}
	
	@Override
	public double getPrice() {
		return pricedHouse.getPrice() * 1.4;
	}

}
