package com.congruent.compulaw.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.congruent.compulaw.domain.LawCategory;
import com.congruent.compulaw.domain.LawSubCategory;

public interface LawCategoryService {

	public LawCategory findById(Long Id);
	public LawCategory save(LawCategory lawCategory);
	public List<LawCategory> findAll();
	public List<LawSubCategory> findAllSubCategories();
	//public LawSubCategory findByLawSubCategoryId(Long id);
	public Page<LawCategory> findAllByPage(PageRequest pageRequest);
	
}
