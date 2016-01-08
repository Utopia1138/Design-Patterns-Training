package com.spg.chapter3.work;

import com.spg.chapter3.House;
import com.spg.chapter3.PriceImpactor;

/**
 * You know what I'm talking about.
 * 
 * @author E043175
 *
 */
public class UglyWallpaper extends PriceImpactor {

	public UglyWallpaper( House pricedHouse ) {
		this.pricedHouse = pricedHouse;
	}

	@Override
	public double getPrice() {
		return pricedHouse.getPrice() * 0.9;
	}

}
