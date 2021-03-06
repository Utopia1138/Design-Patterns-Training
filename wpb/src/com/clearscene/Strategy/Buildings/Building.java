package com.clearscene.Strategy.Buildings;

import com.clearscene.Strategy.Atributes.Heating.GasCentral;
import com.clearscene.Strategy.Atributes.Heating.Heating;
import com.clearscene.Strategy.Atributes.Heating.NoHeating;
import com.clearscene.Strategy.Atributes.Heating.OilFired;
import com.clearscene.Strategy.Atributes.Heating.OpenCoal;

public abstract class Building {

	private int floors;
	private Heating heating;
	
	public Building ( int f, String h ) {
		setFloors( f );
		installHeating( h );
	}
	
	public void installHeating( String h ) {
		if( h.matches( "Gas" ) ) {
			heating = new GasCentral();
		}
		else if ( h.matches( "Oil" ) ) {
			heating = new OilFired();
		}
		else if ( h.matches( "Coal" ) ) {
			heating = new OpenCoal();
		}
		else {
			heating = new NoHeating();
		}
	}

	public int getFloors() {
		return floors;
	}
	
	public void setFloors(int floors) {
		this.floors = floors;
	}

	public void HeatingOn() {
		heating.HeatingOn();
	}

	public void HeatingOff() {
		heating.HeatingOff();
	}
	
	public void HeatingType() {
		System.out.println( "The building has " + heating.HeatingType() + " installed." );
	}
	
	public void describe() {
		System.out.println( "Ww have " + floors + " floors." );
		HeatingType();
	}
}
