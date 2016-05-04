package org.txr.designpatterns.chapter8.politics;

import org.txr.designpatterns.chapter8.politics.model.MP;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import static org.junit.Assert.*;

public class MPFinderSteps {
	
	MPFinder finder;
	String resourceName;
	MP mp;
	String mpName;
	
	@Given("^the TheyWorkForYou MPFinder$")
	public void theTheyWorkForYouMPFinder() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		  finder = new TheyWorkForYouMPFinder();
	}

	@Given("^the PublicWip MPFinder$")
	public void thePublicWipMPFinder() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    finder = new PublicWipMPFinder();
	}
	@Given("^the resource \"([^\"]*)\"$")
	public void theResource(String resource) throws Throwable {
		resourceName = resource;
	}
	@When("^I retrieve a MP with name \"([^\"]*)\"$")
	public void iRetrieveAMPWithName(String name) throws Throwable {
	  mp = finder.findMP(resourceName, name);
	}

	@Then("^the MP exists$")
	public void theMPExists() throws Throwable {
	    assertNotNull(mp);
	}

	@Then("^her name is \"([^\"]*)\"$")
	public void herNameIs(String name) throws Throwable {
	   assertEquals(mp.getName(),name);
	}

	@Then("^her constituency is \"([^\"]*)\"$")
	public void herConstituencyIs(String constituency) throws Throwable {
		 assertEquals(mp.getConstituency(),constituency);
	}

	@Then("^here party is \"([^\"]*)\"$")
	public void herePartyIs(String party) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		 assertEquals(mp.getParty(),"Labour");
	}
}
