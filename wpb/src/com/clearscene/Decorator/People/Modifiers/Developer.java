package com.clearscene.Decorator.People.Modifiers;

import com.clearscene.Decorator.People.Person;

public class Developer extends LifeModifier {

	public Developer( Person p ) {
		this.p = p;
	}

  public String getDescription() {
	  return p.getDescription() + ", Developer";
  }
	
  public int getDeathDate() {
  	return p.getDeathDate() - 10;
  }
	
}