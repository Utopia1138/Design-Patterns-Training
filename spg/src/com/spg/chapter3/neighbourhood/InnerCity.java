package com.spg.chapter3.neighbourhood;

import com.spg.chapter3.House;
import com.spg.chapter3.PriceImpactor;

/**
 * There were no birds singing and the pants were dead and the dirt was messy and bloody from headcrabs.
 * 
 * @author E043175
 *
 */
public class InnerCity extends PriceImpactor {

	public InnerCity( House pricedHouse ) {
		this.pricedHouse = pricedHouse;
	}
	
	@Override
	public double getPrice() {
		return pricedHouse.getPrice() * 0.7;
	}

}
