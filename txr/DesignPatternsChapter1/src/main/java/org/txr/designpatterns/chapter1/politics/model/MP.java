package org.txr.designpatterns.chapter1.politics.model;

public class MP {

	private String name;
	private String constituency;
	private String party;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getConstituency() {
		return constituency;
	}
	public void setConstituency(String constituency) {
		this.constituency = constituency;
	}
	public String getParty() {
		return party;
	}
	public void setParty(String party) {
		this.party = party;
	}
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((constituency == null) ? 0 : constituency.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((party == null) ? 0 : party.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MP other = (MP) obj;
		if (constituency == null) {
			if (other.constituency != null)
				return false;
		} else if (!constituency.equals(other.constituency))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (party == null) {
			if (other.party != null)
				return false;
		} else if (!party.equals(other.party))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "MP [name=" + name + ", constituency=" + constituency + ", party=" + party + "]";
	}
	
	
}
