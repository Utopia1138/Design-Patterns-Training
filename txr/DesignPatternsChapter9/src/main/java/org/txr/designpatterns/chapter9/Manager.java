package org.txr.designpatterns.chapter9;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class Manager extends Employee {
	private List<Employee> subordinates = new ArrayList<Employee>();
	public Manager(String name, int id) {
		super(name, id);
	}
	public void addSuboordinate(Employee...employees) {
		subordinates.addAll(Arrays.asList(employees));
	}
	@Override
	public String getOrganigram() {
		String org =  super.getOrganigram();
		int size = subordinates.size();
		org += "manages " + size +  (size > 1 ? " employees" : " employee") +"\n";
		for (Employee sub : subordinates) {
			org += sub.getOrganigram();
		}
		return org;
	}
	

}
