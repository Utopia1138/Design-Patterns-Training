package com.spg.chapter3.neighbourhood;

import com.spg.chapter3.House;
import com.spg.chapter3.PriceImpactor;

/**
 * Why's it so cheap?
 * 
 * Motivated seller!
 * 
 * @author E043175
 *
 */
public class AncientIndianBurialGround extends PriceImpactor {

	public AncientIndianBurialGround( House pricedHouse ) {
		this.pricedHouse = pricedHouse;
	}
	
	@Override
	public double getPrice() {
		return pricedHouse.getPrice() * 0.1;
	}

}
