package org.txr.designpatterns.chapter1.politics;

import java.util.Map;

import org.txr.designpatterns.chapter1.politics.model.MP;

public interface MPMapper {
	public MP mapToMP(Map<String,Object> map);
}
