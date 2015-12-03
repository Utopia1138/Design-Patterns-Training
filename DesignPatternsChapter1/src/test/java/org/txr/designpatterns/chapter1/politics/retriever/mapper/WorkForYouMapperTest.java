package org.txr.designpatterns.chapter1.politics.retriever.mapper;

import static org.junit.Assert.*;
import static org.hamcrest.core.Is.is;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.txr.designpatterns.chapter1.politics.model.MP;
import org.txr.designpatterns.chapter1.politics.retriever.mapper.WorkForYouMPMapper;

public class WorkForYouMapperTest {

	@Test
	public void testMapping() {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("name", "Alex Salmond");
		map.put("party", "SNP");
		map.put("constituency","Wherever");
		WorkForYouMPMapper mapper = new WorkForYouMPMapper();
		MP mp = mapper.mapToMP(map);
		assertThat(mp.getName(),is("Alex Salmond"));
		assertThat(mp.getParty(),is("SNP"));
		assertThat(mp.getConstituency(),is("Wherever"));
	}

}
