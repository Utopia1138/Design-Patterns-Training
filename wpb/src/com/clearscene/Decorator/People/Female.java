package com.clearscene.Decorator.People;

public class Female extends Person {
	
	int current_age = 0;
	int lifespan = ( 4 * 16 ) + 3; // Four school, plus 3, years.
	
	public Female( int age ) {
		current_age = age;
	}
	
	public String getDescription() {
		return "A female";
	}
	
	public int getDeathDate() {
		return lifespan - current_age;
	}
	
}