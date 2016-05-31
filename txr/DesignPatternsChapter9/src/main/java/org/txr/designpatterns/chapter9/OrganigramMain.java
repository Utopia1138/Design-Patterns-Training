package org.txr.designpatterns.chapter9;

public class OrganigramMain {

	public static void main (String [] args) {
		Manager ceo = new Manager("Boss",1);
		
		Manager m1 = new Manager("Paul",2);
		Manager m2 = new Manager("Albert",3);
		Manager m3 = new Manager("Deborah",4);
		Manager m4 = new Manager("Rufus",5);
		
		Manager m5 = new Manager("Peter",6);
		Manager m6 = new Manager("Malfada",7);
		
		Minion e1 = new Minion("Gregory",8);
		Minion e2 = new Minion("Ivan",9);
		Minion e3 = new Minion("Sergey",10);
		Minion e4 = new Minion("Boris",11);
		Minion e5 = new Minion("Nicolas",12);
		Minion e6 = new Minion("Petrov",13);
		Minion e7 = new Minion("Andrei",14);

		m3.addSuboordinate(e5);
		m2.addSuboordinate(e3,e4);
		m6.addSuboordinate(e6);
		m5.addSuboordinate(m6,e7);
		m1.addSuboordinate(e1,m5,e2);
		
		ceo.addSuboordinate(m1,m2,m3,m4);
		System.out.println(ceo.getOrganigram());
		
		
	}
}
