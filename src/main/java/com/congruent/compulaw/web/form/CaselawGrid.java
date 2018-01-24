package com.congruent.compulaw.web.form;

import java.util.List;

import com.congruent.compulaw.domain.Caselaw;

public class CaselawGrid {
	
	private int totalPages;	
	private int currentPage;	
	private long totalRecords;	
	private List<Caselaw> caselawData;

	public int getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public long getTotalRecords() {
		return totalRecords;
	}

	public void setTotalRecords(long totalRecords) {
		this.totalRecords = totalRecords;
	}

	public List<Caselaw> getCaselawData() {
		return caselawData;
	}

	public void setCaselawData(List<Caselaw> caselawData) {
		this.caselawData = caselawData;
	}

	
}
