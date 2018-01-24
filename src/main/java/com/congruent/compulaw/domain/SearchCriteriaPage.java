package com.congruent.compulaw.domain;

import org.joda.time.DateTime;

public class SearchCriteriaPage {
	
	private String caseTitle;
	private String judge;
	private String counsels;
	private String citations1;
	private String citations2;
	private String citations3;			   
    private String filename;   
    private DateTime created;
    private int offset;
	private int pageSize;

	public String getCaseTitle() {
		return caseTitle;
	}

	public void setCaseTitle(String caseTitle) {
		this.caseTitle = caseTitle;
	}

	public String getJudge() {
		return judge;
	}

	public void setJudge(String judge) {
		this.judge = judge;
	}

	public String getCounsels() {
		return counsels;
	}

	public void setCounsels(String counsels) {
		this.counsels = counsels;
	}

	public String getCitations1() {
		return citations1;
	}

	public void setCitations1(String citations1) {
		this.citations1 = citations1;
	}

	public String getCitations2() {
		return citations2;
	}

	public void setCitations2(String citations2) {
		this.citations2 = citations2;
	}

	public String getCitations3() {
		return citations3;
	}

	public void setCitations3(String citations3) {
		this.citations3 = citations3;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public DateTime getCreated() {
		return created;
	}

	public void setCreated(DateTime created) {
		this.created = created;
	}

	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}	
}
