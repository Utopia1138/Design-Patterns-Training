package org.txr.designpatterns.chapter1.politics.model;

import java.util.Comparator;

public class MPComparator implements Comparator<MP>{

	
	public int compare(MP mp, MP otherMp ) {
			return (mp.getName().compareTo(otherMp.getName()));
	}

}
