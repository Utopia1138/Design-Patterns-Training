package org.txr.designpatterns.chapter9;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import static org.junit.Assert.*;

public class OrganigramSteps {
	Company company;
	Manager ceo;
	Manager paul, julia, spencer, randalph;
	Minion peter, ivan, nicolas,brian,kevin;
	String organigram;
	@Given("^the Company \"([^\"]*)\"$")
	public void theCompany(String name) throws Throwable {
		company = new Company(name);
	}
	@Given("^the Ceo\"([^\"]*)\";$")
	public void theCeo(String name) throws Throwable {
		ceo = new Manager(name,1);
	}
	@Given("^the Company has the Ceo as its ceo$")
	public void theCompanyHasTheCeoAsItsCeo() throws Throwable {
		company.setCeo(ceo);
	}
	
	@Given("^that the ceo has managers Paul, Julia and Spencer$")
	public void thatTheCeoHasManagersPaulJuliaAndSpencer() throws Throwable {
		paul = new Manager("Paul",2);
		julia = new Manager("Julia",3);
		spencer = new Manager("Spencer",4);
		ceo.addSuboordinate(paul,julia,spencer);
	}
	@Given("^the employees Peter, Ivan,Nicolas,Brian,Kevin$")
	public void theEmployeesPeterIvanNicolasBrianKevin() throws Throwable {
	   peter = new Minion("Peter", 5);
	   ivan = new Minion("Ivan", 6);
	   nicolas = new Minion("Nicolas", 7);
	   brian = new Minion("Brian", 8);
	   kevin = new Minion("Kevin", 9);
	}

	@Given("^the manager Randalph$")
	public void theManagerRandalph() throws Throwable {
	   randalph = new Manager("Randalph", 10);
	}

	@Given("^that Paul has (\\d+) employees Peter, Ivan, Randalph, Nicolas$")
	public void thatPaulHasEmployeesPeterIvanRandalphNicolas(int arg1) throws Throwable {
	  paul.addSuboordinate(peter,ivan,randalph,nicolas);
	}

	@Given("^that Randalph has suboordinate employee Brian$")
	public void thatRandalphHasSuboordinateEmployeeBrian() throws Throwable {
	  randalph.addSuboordinate(brian);
	}

	@Given("^that Julia has suboordinate Kevin$")
	public void thatJuliaHasSuboordinateKevin() throws Throwable {
	 julia.addSuboordinate(kevin);;
	}
	@When("^I retrieve the organigram of the company$")
	public void iRetrieveTheOrganigramOfTheCompany() throws Throwable {
	 organigram = company.getCeo().getOrganigram();
	}
	@Then("^the organigram has name of the first employe is \"([^\"]*)\"$")
	public void theOrganigramHasNameOfTheFirstEmployeIs(String name) throws Throwable {
	  assertTrue(organigram, organigram.startsWith("Employee "+name));
	}
	
	@Then("^the organigram shows Paul with (\\d+) suboordinates$")
	public void theOrganigramShowsPaulWithSuboordinates(int arg1) throws Throwable {
	   assertTrue(organigram, organigram.contains("Paul (id:2)\nmanages 4 employees"));
	}

}
