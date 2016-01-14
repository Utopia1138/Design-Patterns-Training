package com.spg.chapter3.owner;

import com.spg.chapter3.House;
import com.spg.chapter3.PriceImpactor;

/**
 * Someone who is really into DIY really improves the overall quality, but 
 * there are a couple of places where things didn't quite go to plan...
 * 
 * @author E043175
 *
 */
public class DIYGuy extends PriceImpactor {

	public DIYGuy( House pricedHouse ) {
		this.pricedHouse = pricedHouse;
	}
	
	@Override
	public double getPrice() {
		return pricedHouse.getPrice() * 1.5 - 10000;
	}
	
}
