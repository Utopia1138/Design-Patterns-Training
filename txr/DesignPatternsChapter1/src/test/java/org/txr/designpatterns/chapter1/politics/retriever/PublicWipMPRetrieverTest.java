package org.txr.designpatterns.chapter1.politics.retriever;

import static org.junit.Assert.*;
import static org.junit.matchers.JUnitMatchers.hasItems;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.txr.designpatterns.chapter1.politics.model.MP;
import org.txr.designpatterns.chapter1.politics.retriever.mapper.PublicWipMPMapper;

public class PublicWipMPRetrieverTest {
	@SuppressWarnings(value = { "deprecation" })
	@Test
	public void test() throws Exception {
		PublicWipMPRetriever retriever = new PublicWipMPRetriever();
		PublicWipMPMapper mapper = new PublicWipMPMapper();
		retriever.setMpMapper(mapper);
		List <MP> mps = retriever.retrieveMPS();
		assertEquals(mps.size(),650);
		
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
		
		assertThat(mps, hasItems(expected.toArray(new MP[expected.size()])));
	}

}
