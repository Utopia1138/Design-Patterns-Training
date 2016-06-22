package org.txr.designpatterns.chapter8.politics;



import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.SnippetType;
import cucumber.api.junit.Cucumber;
@RunWith(Cucumber.class)
@CucumberOptions( 
		format = { "pretty","html:/target/cucumber"},
		features = "classpath:cucumber/MPFinder.feature",
		snippets = SnippetType.CAMELCASE
)
public class MPFinderTest {

	
}
