package org.txr.designpatterns.chapter1.politics.retriever;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.txr.designpatterns.chapter1.politics.MPMapper;
import org.txr.designpatterns.chapter1.politics.MPRetriever;
import org.txr.designpatterns.chapter1.politics.model.MP;
import org.txr.designpatterns.chapter1.politics.model.MPComparator;

public class PublicWipMPRetriever implements MPRetriever{
	public static final String FILE = "C:\\dev\\DesignPatternsChapter1\\src\\main\\resources\\publicwip.htm";
	
	private MPMapper mpMapper;
	
	public List<MP> retrieveMPS() throws Exception {
		List<MP> mps = new ArrayList<MP>();
		Document doc = Jsoup.parse(new File(FILE), "UTF-8");
		Elements elements = doc.select("table.mps");
		Element table = elements.first();
		
		Elements rows = table.getElementsByTag("tr");
		Iterator<Element> it = rows.iterator();
		while (it.hasNext()) {
			Element element = it.next();
			if (!element.hasClass("headings")) {
				Map<String,Object> map = new HashMap<String,Object>();
				map.put("name", element.child(0).child(0).text());
				map.put("constituency", element.child(1).child(0).text());
				map.put("party", element.child(2).text());
			    mps.add(mpMapper.mapToMP(map));
			}
			
		}
		Collections.sort(mps,new MPComparator());
		return mps;
	}
	
	public void setMpMapper(MPMapper mpMapper) {
		this.mpMapper = mpMapper;
	}

}
