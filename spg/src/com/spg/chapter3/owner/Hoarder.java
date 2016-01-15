package com.spg.chapter3.owner;

import com.spg.chapter3.House;
import com.spg.chapter3.PriceImpactor;

/**
 * A hoarder leaves the house a total mess and fills up a room with junk.
 * 
 * @author E043175
 *
 */
public class Hoarder extends PriceImpactor {

	public Hoarder( House pricedHouse ) {
		this.pricedHouse = pricedHouse;
	}

	
	@Override
	public double getPrice() {
		pricedHouse.setNumberOfRooms( pricedHouse.getNumberOfRooms() - 1 ); 
		
		return pricedHouse.getPrice() * 0.3;
	}

}
