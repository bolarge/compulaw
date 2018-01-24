package com.congruent.compulaw.web.form;

import java.util.List;

import com.congruent.compulaw.domain.LawCategory;

public class LawCategoryGrid {

	private int totalPages;

	private int currentPage;

	private long totalRecords;

	private List<LawCategory> lawCategoryData;

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

	public List<LawCategory> getLawCategoryData() {

		return lawCategoryData;
	}

	public void setLawCategoryData(List<LawCategory> actData) {
		this.lawCategoryData = actData;
	}

}
