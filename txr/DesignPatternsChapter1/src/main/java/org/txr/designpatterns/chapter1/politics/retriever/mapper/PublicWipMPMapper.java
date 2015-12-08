package org.txr.designpatterns.chapter1.politics.retriever.mapper;

import java.util.Map;

import org.txr.designpatterns.chapter1.politics.MPMapper;
import org.txr.designpatterns.chapter1.politics.model.MP;

public class PublicWipMPMapper implements MPMapper {
	public MP mapToMP(Map<String,Object> map) {
		MP mp = new MP();
		mp.setConstituency((String)map.get("constituency"));
		mp.setName((String)map.get("name"));
		mp.setParty(mapParty((String)map.get("party")));
		return mp;
	}
	
	public String mapParty(String party) {
		if ("Lab".equals(party)) {
			return "Labour";
		}
		if ("Con".equals(party)) {
			return "Conservative";
		}
		if ("SNP".equals(party)) {
			return "Scottish National Party";
		}
		if ("DUP".equals(party)) {
			return party;
		}
		if ("PC".equals(party)) {
			return "Plaid Cymru";
		}
		if ("Green".equals(party)) {
			return party;
		}
		if ("LDem".equals(party)) {
			return "Liberal Democrat";
		}
		return party;
	}

}
