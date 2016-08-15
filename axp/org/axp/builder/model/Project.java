package org.axp.builder.model;

import java.time.LocalDate;

public class Project {
	private String projectName;
	private Employee devLead;
	private Project parent;
	private LocalDate start;
	private LocalDate end;
	private boolean complete;
	
	public String getProjectName() {
		return projectName;
	}
	
	public Project setProjectName(String projectName) {
		this.projectName = projectName;
		return this;
	}
	
	public Employee getDevLead() {
		return devLead;
	}
	
	public Project setDevLead(Employee devLead) {
		this.devLead = devLead;
		return this;
	}
	
	public Project getParent() {
		return parent;
	}
	
	public Project setParent(Project parent) {
		this.parent = parent;
		return this;
	}
	
	public LocalDate getStart() {
		return start;
	}
	
	public Project setStart(LocalDate start) {
		this.start = start;
		return this;
	}
	
	public LocalDate getEnd() {
		return end;
	}
	
	public Project setEnd(LocalDate end) {
		this.end = end;
		return this;
	}
	
	public boolean isComplete() {
		return complete;
	}
	
	public Project setComplete(boolean complete) {
		this.complete = complete;
		return this;
	}
	
	public String toString() {
		return "{ " + projectName +
				"; lead by " + (( devLead == null ) ? "NONE" : devLead.getCommonName()) +
				"; parent " + (( parent == null ) ? "NONE" : parent.getProjectName()) +
				"; starts " + (( start == null ) ? "UNKNOWN" : start) +
				"; ends " + (( end == null ) ? "UNKNOWN" : end) +
				"; " + ( complete ? "COMPLETE" : "ONGOING" ) + " }";
	}
}
