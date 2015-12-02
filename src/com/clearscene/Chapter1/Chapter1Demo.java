package com.clearscene.Chapter1;

import com.clearscene.Chapter1.Buildings.Building;
import com.clearscene.Chapter1.Buildings.Flat;
import com.clearscene.Chapter1.Buildings.House;

public class Chapter1Demo {

	public static void main(String[] args) {

		Building flat1 = new Flat( 1, "Gas" );
		flat1.describe();
		flat1.HeatingOn();
		flat1.HeatingOff();
		
		System.out.println("==========");

		Building house1 = new House( 2, 5, "Gas" );
		house1.describe();
		house1.HeatingOn();
		house1.HeatingOff();
		
		System.out.println("==========");

		Building flat2 = new Flat( 1, "" );
		flat2.describe();
		flat2.HeatingOn();
		flat2.HeatingOff();
		
		System.out.println("==========");

		Building house2 = new House( 1, 1, "Coal" );
		house2.describe();
		house2.HeatingOn();
		house2.HeatingOff();
	}

}
