package com.congruent.compulaw.domain;

import java.io.Serializable;
import java.util.Arrays;

public class CaselawSummary implements Serializable {

	private static final long serialVersionUID = 1L;
	private String title;
	private String judges;
	private String counsels;
	private byte[] caseDocument;
	
	public CaselawSummary(String title, String judges, String counsels,
			byte[] caseDocument) {
		super();
		this.title = title;
		this.judges = judges;
		this.counsels = counsels;
		this.caseDocument = caseDocument;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getJudges() {
		return judges;
	}
	public void setJudges(String judges) {
		this.judges = judges;
	}
	public String getCounsels() {
		return counsels;
	}
	public void setCounsels(String counsels) {
		this.counsels = counsels;
	}
	public byte[] getCaseDocument() {
		return caseDocument;
	}
	public void setCaseDocument(byte[] caseDocument) {
		this.caseDocument = caseDocument;
	}
	@Override
	public String toString() {
		return "CaselawSummary [title=" + title + ", judges=" + judges
				+ ", counsels=" + counsels + ", caseDocument="
				+ Arrays.toString(caseDocument) + "]";
	}
	
	

}
