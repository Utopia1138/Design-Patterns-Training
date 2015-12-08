package org.txr.designpatterns.chapter1.politics;

import java.util.List;

import org.txr.designpatterns.chapter1.politics.model.MP;

public interface OutputFormatter {
	public String format(List<MP> list);
}
