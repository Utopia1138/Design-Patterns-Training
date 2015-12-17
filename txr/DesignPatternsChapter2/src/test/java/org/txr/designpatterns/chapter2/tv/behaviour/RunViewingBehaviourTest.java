package org.txr.designpatterns.chapter2.tv.behaviour;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.SnippetType;
import cucumber.api.junit.Cucumber;
@RunWith(Cucumber.class)
@CucumberOptions( 
		format = { "pretty","html:/target/cucumber"},
		features = "classpath:cucumber/viewingBehaviour.feature",
		snippets = SnippetType.CAMELCASE
)
public class RunViewingBehaviourTest {

}
