package com.congruent.compulaw.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class ActSection implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String sectionId;
	private String sectionTitle;
	
	protected ActSection(){}
	
	public ActSection(String sectionTitle) {
		super();
		this.sectionTitle = sectionTitle;
	}

	@Column(name = "SECTION_ID")
	public String getSectionId() {
		return sectionId;
	}

	public void setSectionId(String sectionId) {
		this.sectionId = sectionId;
	}

	@Column(name = "SECTION_TITLE")
	public String getSectionTitle() {
		return sectionTitle;
	}

	public void setSectionTitle(String title) {
		this.sectionTitle = title;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((sectionId == null) ? 0 : sectionId.hashCode());
		result = prime * result
				+ ((sectionTitle == null) ? 0 : sectionTitle.hashCode());
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
		ActSection other = (ActSection) obj;
		if (sectionId == null) {
			if (other.sectionId != null)
				return false;
		} else if (!sectionId.equals(other.sectionId))
			return false;
		if (sectionTitle == null) {
			if (other.sectionTitle != null)
				return false;
		} else if (!sectionTitle.equals(other.sectionTitle))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ActSection [sectionTitle=" + sectionTitle + "]";
	}
	
	

}
