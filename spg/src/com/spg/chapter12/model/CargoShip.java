package com.spg.chapter12.model;


public class CargoShip {

	private int size;
	private int load;
	
	public CargoShip( int size ) {
		this.size = size;
		this.load = load;
	}

	public int getSize() {
		return size;
	}

	public void setSize( int size ) {
		this.size = size;
	}
	
	public int getLoad() {
		return load;
	}

	public void setLoad( int load ) {
		this.load = load;
	}
	
	public int addLoad( int load ) {			
		int loaded = load;
		
		if ( this.load + load > size ) {
			loaded = size - this.load;
			this.load = size;
		}
		
		return loaded;
	}
}
