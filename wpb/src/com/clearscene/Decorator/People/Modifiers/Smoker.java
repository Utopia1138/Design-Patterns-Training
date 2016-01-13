package com.clearscene.Decorator.People.Modifiers;

import com.clearscene.Decorator.People.Person;

public class Smoker extends LifeModifier {
	
	public Smoker( Person p ) {
		this.p = p;
	}

  public String getDescription() {
	  return p.getDescription() + ", Smoker";
  }
	
  public int getDeathDate() {
  	return p.getDeathDate() - 30;
  }
	
}