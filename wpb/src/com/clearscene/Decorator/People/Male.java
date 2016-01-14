package com.clearscene.Decorator.People;

public class Male extends Person {
	
	int current_age = 0;
	int lifespan = ( 4 * 16 ) + 10; // Four school, plus 10, years.
	
	public Male( int age ) {
		current_age = age;
	}
	
	public String getDescription() {
		return "A male";
	}
	
	public int getDeathDate() {
		return lifespan - current_age;
	}
	
}