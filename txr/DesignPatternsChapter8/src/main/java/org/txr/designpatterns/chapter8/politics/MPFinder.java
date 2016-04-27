package org.txr.designpatterns.chapter8.politics;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.txr.designpatterns.chapter8.politics.model.MP;

public abstract class MPFinder {
	
	public final MP findMP(String resourceName, String name) {
		MP mp = null;
		String content = fileToText(resourceName);
		List<Map<String,String>> rawResultList = retrieveData(content);
		List<MP> mps = mapData(rawResultList);
		mp = findMp(mps,name);
		return mp;
	}
	
	public String fileToText(String resourceName) {
		File file = new File(resourceName);
		if (!file.exists()) {
			throw new IllegalArgumentException("file " + resourceName + " does not exist");
		}
		String text ="";
		try {
			text = new Scanner(file).useDelimiter("\\A").next();
		} catch (FileNotFoundException e) {
			throw new IllegalArgumentException("file " + resourceName + " does not exist");
		}
		return text;
	}
	
	public abstract List<Map<String,String>> retrieveData (String rawContent);
	public abstract List<MP> mapData(List<Map<String,String>> rawList);
	
	public MP findMp(List<MP> mps, String name) {
		MP selectedMp = null;
		for (MP mp: mps ) {
			if (mp.getName().equalsIgnoreCase(name)) {
				selectedMp = mp;
			}
		}
		return selectedMp;
	}
}
