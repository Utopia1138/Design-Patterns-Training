package org.txr.designpatterns.chapter1.politics.retriever;

import static org.junit.Assert.*;
import static org.hamcrest.core.Is.is;
import static org.junit.matchers.JUnitMatchers.hasItems;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.junit.Test;
import org.txr.designpatterns.chapter1.politics.model.MP;
import org.txr.designpatterns.chapter1.politics.retriever.mapper.WorkForYouMPMapper;

public class WorkForYouMPRetrieverTest {
	
	@Test
	public void testJsonMap() throws Exception {
		WorkForYouMPRetriever retriever  = new WorkForYouMPRetriever();
		@SuppressWarnings("resource")
		String text = new Scanner(this.getClass().getResourceAsStream("twfymps.json"), "UTF-8").useDelimiter("\\A").next();
		List<Map<String,Object>> list = retriever.getJsonMPS(text);
		assertThat(list.size(), is(649));
	}
	@SuppressWarnings(value = { "deprecation" })
	@Test
	public void testRetrieveMps() throws Exception {
		WorkForYouMPRetriever retriever  = new WorkForYouMPRetriever();
		retriever.setMapper(new WorkForYouMPMapper());
		List<MP> list = retriever.retrieveMPS();
		assertThat(list.size(), is(649));
		
		MP salmond = new MP();
		salmond.setName("Alex Salmond");
		salmond.setParty("Scottish National Party");
		salmond.setConstituency("Gordon");
		
		MP cameron = new MP();
		cameron.setName("David Cameron");
		cameron.setParty("Conservative");
		cameron.setConstituency("Witney");
		
		List<MP> expected = new ArrayList<MP>();
		expected.add(salmond);
		expected.add(cameron);
		
		assertThat(list, hasItems(expected.toArray(new MP[expected.size()])));
	
	}
}
