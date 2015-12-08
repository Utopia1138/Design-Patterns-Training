package org.txr.designpatterns.chapter1.politics.retriever.mapper;

import java.util.Map;

import org.txr.designpatterns.chapter1.politics.MPMapper;
import org.txr.designpatterns.chapter1.politics.model.MP;

public class WorkForYouMPMapper implements MPMapper{
	
	public MP mapToMP(Map<String,Object> map) {
		MP mp = new MP();
		mp.setConstituency((String)map.get("constituency"));
		mp.setName((String)map.get("name"));
		mp.setParty((String)map.get("party"));
		return mp;
	}
}
