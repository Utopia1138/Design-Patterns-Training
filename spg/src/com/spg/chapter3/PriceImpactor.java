package com.spg.chapter3;


public abstract class PriceImpactor implements House {

	protected House pricedHouse;
	
	@Override
	public abstract double getPrice();

	@Override
	public int getNumberOfRooms() {
		return pricedHouse.getNumberOfRooms();
	}

	@Override
	public int setNumberOfRooms( int no ) {
		return pricedHouse.setNumberOfRooms( no );
	}

}
