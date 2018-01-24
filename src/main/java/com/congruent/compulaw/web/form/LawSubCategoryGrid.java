package com.congruent.compulaw.web.form;

import java.util.List;

import com.congruent.compulaw.domain.LawSubCategory;

public class LawSubCategoryGrid {
	
	private int totalPages;

	private int currentPage;

	private long totalRecords;

	private List<LawSubCategory> lawSubCategoryData;

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

	public List<LawSubCategory> getLawSubCategoryData() {

		return lawSubCategoryData;
	}

	public void setLawSubCategoryData(List<LawSubCategory> subData) {
		this.lawSubCategoryData = subData;
	}

}
