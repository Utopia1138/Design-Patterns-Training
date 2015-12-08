package com.spg.chapter2;


public class Coords {

	private int xCoord;
	private int yCoord;
	// Assume we have a square area
	private int coordMax;
	private int coordMin;

	public Coords( int x, int y, int maximum ) {
		this.xCoord = x;
		this.yCoord = y;
		this.coordMax = maximum; 
		this.coordMin = 0; // Avoid any nasty negative numbers
	}
	
	public int getXCoord() {
		return xCoord;
	}

	public void setXCoord( int xCoord ) {
		if ( xCoord < coordMin ) { 
			this.xCoord = coordMin;
		}
		else if ( xCoord > coordMax ) {
			this.xCoord = coordMax;
		}
		else { 
			this.xCoord = xCoord;
		}
	}

	public int getYCoord() {
		return yCoord;
	}

	public void setYCoord( int yCoord ) {
		if ( yCoord < coordMin ) { 
			this.yCoord = coordMin;
		}
		else if ( yCoord > coordMax ) {
			this.yCoord = coordMax;
		}
		else { 
			this.yCoord = yCoord;
		}

	}
	
	public int calculateDistance( Coords target ) {
		
		int xDistance = Math.abs( xCoord - target.getXCoord() );
		int yDistance = Math.abs( yCoord - target.getYCoord() );
		
		return (int) Math.floor( Math.sqrt( Math.pow( xDistance, 2 ) + Math.pow(  yDistance, 2 ) ) );
	}
}
