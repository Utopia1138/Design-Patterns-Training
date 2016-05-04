package org.txr.designpatterns.chapter8.politics;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.txr.designpatterns.chapter8.politics.model.MP;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class TheyWorkForYouMPFinder extends MPFinder {

	@Override
	public List<Map<String, String>> retrieveData(String rawContent) {
		List<Map<String, String>> rawList = new ArrayList<Map<String, String>>();
		ObjectMapper mapper = new ObjectMapper();
		TypeReference<List<Map<String, Object>>> typeRef = new TypeReference<List<Map<String, Object>>>() {
		};
		try {
			rawList = mapper.readValue(rawContent, typeRef);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		return rawList;
	}

	@Override
	public List<MP> mapData(List<Map<String, String>> rawList) {
		List<MP>mps = new ArrayList<MP>();
		for (Map<String,String> map: rawList) {
			MP mp = new MP();
			mp.setConstituency(map.get("constituency"));
			mp.setName(map.get("name"));
			mp.setParty(map.get("party"));
			mps.add(mp);
		}
		return mps;
	}

}
