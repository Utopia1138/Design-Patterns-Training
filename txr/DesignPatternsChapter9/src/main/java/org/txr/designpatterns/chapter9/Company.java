package org.txr.designpatterns.chapter9;

public class Company {
	private String name;
	private Employee ceo;
	
	
	public Company(String name) {
		super();
		this.name = name;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Employee getCeo() {
		return ceo;
	}
	public void setCeo(Employee ceo) {
		this.ceo = ceo;
	}
	
	
}
