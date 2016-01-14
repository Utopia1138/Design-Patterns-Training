package com.spg.chapter3.work;

import com.spg.chapter3.House;
import com.spg.chapter3.PriceImpactor;

/**
 * Added two new rooms, but at the cost of damaging the original appearance of the house.
 * 
 * @author E043175
 *
 */
public class Extension extends PriceImpactor {

	public Extension( House pricedHouse ) {
		this.pricedHouse = pricedHouse;
	}
	
	@Override
	public double getPrice() {
		pricedHouse.setNumberOfRooms( pricedHouse.getNumberOfRooms() + 2 ); 
		
		return pricedHouse.getPrice() * 0.9;
	}

}
