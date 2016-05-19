package org.txr.designpatterns.chapter9;

import static org.junit.Assert.*;

import org.junit.Test;

public class ManagerTest {

	@Test
	public void testOrganigramDepth1() {
		Manager m = new Manager("Paul",1);
		Minion e1 = new Minion("Patrick",2);
		Minion e2 = new Minion("Colin",3);
		m.addSuboordinate(e1,e2);
		String expected = "Employee Paul (id:1)\nmanages 2 employees\nEmployee Patrick (id:2)\n" +
		"manages no-one\nEmployee Colin (id:3)\nmanages no-one\n";
		assertEquals(expected, m.getOrganigram());
	}
	@Test
	public void testOrganigramDepth2() {
		Manager m1 = new Manager("Paul",1);
		Manager m2 = new Manager("Patrick",2);
		Minion e1 = new Minion("Colin",3);
		Minion e2 = new Minion("Sergey",4);
		m2.addSuboordinate(e2);
		m1.addSuboordinate(m2,e1);
		String expected = "Employee Paul (id:1)\nmanages 2 employees\nEmployee Patrick (id:2)\n" +
		"manages 1 employee\nEmployee Sergey (id:4)\nmanages no-one\nEmployee Colin (id:3)\nmanages no-one\n";
		assertEquals(expected, m1.getOrganigram());
	}
	@Test
	// a manager with zero employee is not a minion !!!
	public void testOrganigramManagerWithZeroEmployee() {
		Manager m1 = new Manager("Paul",1);
		Manager m2 = new Manager("Patrick",2);
		Minion e1 = new Minion("Colin",3);
		m1.addSuboordinate(m2,e1);
		String expected = "Employee Paul (id:1)\nmanages 2 employees\nEmployee Patrick (id:2)\n" +
		"manages 0 employee\nEmployee Colin (id:3)\nmanages no-one\n";
		assertEquals(expected, m1.getOrganigram());
	}


}
