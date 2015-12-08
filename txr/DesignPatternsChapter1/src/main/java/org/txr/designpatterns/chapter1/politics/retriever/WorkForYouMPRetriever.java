package org.txr.designpatterns.chapter1.politics.retriever;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.txr.designpatterns.chapter1.politics.MPMapper;
import org.txr.designpatterns.chapter1.politics.MPRetriever;
import org.txr.designpatterns.chapter1.politics.model.MP;
import org.txr.designpatterns.chapter1.politics.model.MPComparator;

public class WorkForYouMPRetriever implements MPRetriever{
	//public static final String FILE = "C:\\dev\\DesignPatternsChapter1\\src\\main\\resources\\twfymps.json";
	private MPMapper mpMapper;
	
	public List <MP> retrieveMPS() throws Exception {
		List <MP> mps = new ArrayList<MP>();
		@SuppressWarnings("resource")
		String text = new Scanner(this.getClass().getResourceAsStream("twfymps.json"), "UTF-8").useDelimiter("\\A").next();
		List <Map<String,Object>> rawList = getJsonMPS(text);
		for (Map<String,Object> map: rawList) {
			mps.add( mpMapper.mapToMP(map));
		}
		Collections.sort(mps,new MPComparator());
		return mps;
		}
	
	public List<Map<String,Object>> getJsonMPS(String text) throws Exception{
	
		
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		ObjectMapper mapper = new ObjectMapper();
		TypeReference<List<Map<String,Object>>> typeRef = new TypeReference<List<Map<String,Object>>>() {
		}; 
		list = mapper.readValue(text,typeRef);
		return list;
	}

	public void setMapper(MPMapper  mpMapper) {
		this.mpMapper =  mpMapper;
	}
	
	
	
	
}
