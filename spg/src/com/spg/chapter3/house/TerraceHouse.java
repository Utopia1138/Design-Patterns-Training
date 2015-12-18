package com.spg.chapter3.house;

import com.spg.chapter3.House;


public class TerraceHouse implements House {

	private int numberOfRooms;
	
	@Override
	public double getPrice() {
		return 50000 * this.getNumberOfRooms();
	}

	@Override
	public int getNumberOfRooms() {
		return numberOfRooms;
	}

	@Override
	public int setNumberOfRooms( int no ) {
		return numberOfRooms = no;
	}

}
