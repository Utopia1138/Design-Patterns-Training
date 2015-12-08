package org.txr.designpatterns.chapter1.politics.formatter;

import java.util.List;

import org.txr.designpatterns.chapter1.politics.OutputFormatter;
import org.txr.designpatterns.chapter1.politics.model.MP;

public class DefaultMPFormatter implements OutputFormatter {

	public String format(List<MP> list) {
		StringBuilder builder = new StringBuilder();
		for (MP mp : list) {
			builder.append(mp.toString() + "\n");
		}
		return builder.toString();
	}

}
