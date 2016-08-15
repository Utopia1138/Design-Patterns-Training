package org.axp.builder.model;

public class Employee {
	private String commonName;
	private String fullName;
	private boolean permanent;
	private Department department;
	
	public String getCommonName() {
		return commonName;
	}
	
	public Employee setCommonName(String commonName) {
		this.commonName = commonName;
		return this;
	}
	
	public String getFullName() {
		return fullName;
	}
	
	public Employee setFullName(String fullName) {
		this.fullName = fullName;
		return this;
	}
	
	public boolean isPermanent() {
		return permanent;
	}
	
	public Employee setPermanent(boolean permanent) {
		this.permanent = permanent;
		return this;
	}
	
	public Department getDepartment() {
		return department;
	}
	
	public Employee setDepartment(Department department) {
		this.department = department;
		return this;
	}
	
	public String toString() {
		return "{ " + commonName + "; " + fullName + "; " + department + "; " + ( permanent ? "PERM" : "CONTR" ) + " }";
	}
}
