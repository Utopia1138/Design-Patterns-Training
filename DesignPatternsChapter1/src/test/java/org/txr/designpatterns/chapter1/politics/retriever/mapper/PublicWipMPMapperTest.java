package org.txr.designpatterns.chapter1.politics.retriever.mapper;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.core.Is.is;
import org.junit.Test;
import org.txr.designpatterns.chapter1.politics.model.MP;

public class PublicWipMPMapperTest {

	@Test
	public void testMappingParty() {
			PublicWipMPMapper mapper = new PublicWipMPMapper();
			assertThat (mapper.mapParty("XXX"),is("XXX"));
			assertThat (mapper.mapParty("Lab"),is("Labour"));
			assertThat (mapper.mapParty("Con"),is("Conservative"));
			assertThat (mapper.mapParty("PC"),is("Plaid Cymru"));
			assertThat (mapper.mapParty("SNP"),is("Scottish National Party"));
			assertThat (mapper.mapParty("DUP"),is("DUP"));
			assertThat(mapper.mapParty("LDem"),is ("Liberal Democrat"));
	}
	@Test
	public void testMapMP() {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("name", "Jeremy Corbyn");
		map.put("constituency", "Islington North");
		map.put("party", "Lab");
		PublicWipMPMapper mapper = new PublicWipMPMapper();
		MP mp = mapper.mapToMP(map);
		assertThat(mp.getName(),is("Jeremy Corbyn"));
		assertThat(mp.getConstituency(),is("Islington North"));
		assertThat(mp.getParty(),is("Labour"));
	}

}
