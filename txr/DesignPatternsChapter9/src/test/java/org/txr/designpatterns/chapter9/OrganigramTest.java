package org.txr.designpatterns.chapter9;

import org.junit.Test;
import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.SnippetType;
import cucumber.api.junit.Cucumber;
@RunWith(Cucumber.class)
@CucumberOptions( 
		format = { "pretty","html:/target/cucumber"},
		features = "classpath:cucumber/organigram.feature",
		snippets = SnippetType.CAMELCASE
)

public class OrganigramTest {

	
}
