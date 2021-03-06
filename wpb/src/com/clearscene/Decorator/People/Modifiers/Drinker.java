package com.clearscene.Decorator.People.Modifiers;

import com.clearscene.Decorator.People.Person;

public class Drinker extends LifeModifier {
	
	public Drinker( Person p ) {
		this.p = p;
	}

  public String getDescription() {
	  return p.getDescription() + ", Drinker";
  }
	
  public int getDeathDate() {
  	return p.getDeathDate() - 10;
  }
	
}