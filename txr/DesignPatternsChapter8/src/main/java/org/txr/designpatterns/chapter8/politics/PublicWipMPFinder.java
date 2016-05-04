package org.txr.designpatterns.chapter8.politics;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import org.txr.designpatterns.chapter8.politics.model.MP;

public class PublicWipMPFinder extends MPFinder {

	@Override
	public List<Map<String, String>> retrieveData(String rawContent)  {
		List<Map<String, String>> mps = new ArrayList<Map<String, String>>();
		Document doc = Jsoup.parse(rawContent, "UTF-8");
		Elements elements = doc.select("table.mps");
		Element table = elements.first();
		
		Elements rows = table.getElementsByTag("tr");
		Iterator<Element> it = rows.iterator();
		while (it.hasNext()) {
			Element element = it.next();
			if (!element.hasClass("headings")) {
				Map<String,String> map = new HashMap<String,String>();
				map.put("name", element.child(0).child(0).text());
				map.put("constituency", element.child(1).child(0).text());
				map.put("party", element.child(2).text());
				mps.add(map);
			} else {
				// nothing
			}
		}
		return mps;
	}

	@Override
	public List<MP> mapData(List<Map<String, String>> rawList) {
		List<MP>mps = new ArrayList<MP>();
		for (Map<String,String> map: rawList) {
			MP mp = new MP();
			mp.setConstituency(map.get("constituency"));
			mp.setName(map.get("name"));
			mp.setParty(mapParty(map.get("party")));
			mps.add(mp);
		}
		return mps;
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
