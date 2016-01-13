package com.clearscene.Decorator.People.Modifiers;

import com.clearscene.Decorator.People.Person;

public class Runner extends LifeModifier {
	
	public Runner( Person p ) {
		this.p = p;
	}

  public String getDescription() {
	  return p.getDescription() + ", Runner";
  }
	
  public int getDeathDate() {
  	return p.getDeathDate() + 10;
  }
	
}