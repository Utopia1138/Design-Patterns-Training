package com.clearscene.Decorator.People.Modifiers;

import com.clearscene.Decorator.People.Person;

public class Drinker extends LifeModifier {
	
	Person p;
	
	public Drinker( Person p ) {
		this.p = p;
	}

  public String getDescription() {
	  return p.getDescription() + ", Developer";
  }
	
  public int getDeathDate() {
  	return p.getDeathDate() - 10;
  }
	
}