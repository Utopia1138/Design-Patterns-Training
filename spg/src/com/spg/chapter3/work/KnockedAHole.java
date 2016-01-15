package com.spg.chapter3.work;

import com.spg.chapter3.House;
import com.spg.chapter3.PriceImpactor;

/**
 * Turned two rooms into one, making the place feel more spacious but at the 
 * reducing the total number of rooms by one.
 * 
 * @author E043175
 *
 */
public class KnockedAHole extends PriceImpactor {

	public KnockedAHole( House pricedHouse ) {
		this.pricedHouse = pricedHouse;
	}

	@Override
	public double getPrice() {
		pricedHouse.setNumberOfRooms( pricedHouse.getNumberOfRooms() - 1 ); 
		
		return pricedHouse.getPrice() * 1.2;
	}
}
