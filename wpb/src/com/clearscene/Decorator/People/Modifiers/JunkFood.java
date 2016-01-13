package com.clearscene.Decorator.People.Modifiers;

import com.clearscene.Decorator.People.Person;

public class JunkFood extends LifeModifier {
	
	public JunkFood( Person p ) {
		this.p = p;
	}

  public String getDescription() {
	  return p.getDescription() + ", JunkFood Eater";
  }
	
  public int getDeathDate() {
  	return p.getDeathDate() - 10;
  }
	
}