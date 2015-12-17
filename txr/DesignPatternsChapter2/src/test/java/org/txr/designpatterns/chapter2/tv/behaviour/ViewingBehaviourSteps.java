package org.txr.designpatterns.chapter2.tv.behaviour;

import org.txr.designpatterns.chapter2.tv.DefaultViewer;
import org.txr.designpatterns.chapter2.tv.Program;
import org.txr.designpatterns.chapter2.tv.ProgramType;
import org.txr.designpatterns.chapter2.tv.Television;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static org.junit.Assert.*;

public class ViewingBehaviourSteps {
	Television tv;
	DefaultViewer viewer;
	HorrorFan behaviour;
	Program program;
	
	@Given("^a TV$")
	public void aTV() throws Throwable {
		tv = new Television();
	}

	@Given("^a horror program$")
	public void aHorrorProgram() throws Throwable {
	  program = new Program("Nightmare on Elm Street", ProgramType.HORROR_MOVIES);
	}
	@Given("^a viewer$")
	public void aViewer() throws Throwable {
		viewer = new DefaultViewer("harry", tv);
	}

	@Given("^a horrorFan Behaviour$")
	public void aHorrorFanBehaviourRatingHorrorFilmAs() throws Throwable {
	   behaviour = new HorrorFan();
	   
	}

	@When("^the viewer has the horror fan behaviour$")
	public void theViewerHasTheHorrorFanBehaviour() throws Throwable {
	   viewer.setViewingBehaviour(behaviour);
	}

	@When("^watches a horror program on the TV$")
	public void watchesAHorrorProgramOnTheTV() throws Throwable {
	  tv.setProgram(program);
	}

	@Then("^the viewer current interest will be (\\d+)$")
	public void theViewerCurrentInterestWillBe(int expectedRating) throws Throwable {
	   assertEquals(viewer.getCurrentInterest(),expectedRating);
	}
}
