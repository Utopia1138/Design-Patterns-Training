package com.clearscene.Decorator.People.Modifiers;

import com.clearscene.Decorator.People.Person;

public abstract class LifeModifier extends Person {
	
	Person p;
	
	public abstract String getDescription();
	
}
