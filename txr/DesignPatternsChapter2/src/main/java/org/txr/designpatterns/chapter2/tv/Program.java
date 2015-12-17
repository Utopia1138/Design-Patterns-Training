package org.txr.designpatterns.chapter2.tv;

public class Program {
	private String title;
	private ProgramType type;
	public Program (String title, ProgramType type) {
		this.title = title;
		this.type = type;
	}
	public String getTitle() {
		return title;
	}
	public ProgramType getType() {
		return type;
	}

}
