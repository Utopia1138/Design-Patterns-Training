package org.txr.designpatterns.chapter1.politics;

import java.util.List;

import org.txr.designpatterns.chapter1.politics.model.MP;


public interface MPRetriever {
	public List <MP> retrieveMPS() throws Exception;
}
