package com.jpappe.ch6.command;

public abstract class ComparableCommand implements Command, Comparable<ComparableCommand> {

	protected int priority;

	public int compareTo(ComparableCommand other) {
		return (this.priority - other.priority);
	}

}
