package com.congruent.compulaw.service;

import java.util.List;
import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.congruent.compulaw.domain.LawSubCategory;

public interface LawSubCategoryService {
	
	public LawSubCategory findById(Long Id);
	public LawSubCategory findByLawSubCategoryId(Long id);
	public List<LawSubCategory> findAll();
	public List<LawSubCategory> findAllSubCategories();
	public Page<LawSubCategory> findAllByPage(Pageable pageable);
	
	public LawSubCategory save(LawSubCategory lawSubCategory);	
	public void save(Set<LawSubCategory> theSubs);
	public LawSubCategory findByName(String name);
	
	
	//public List<LawCategory> findAllParentCategory();	
	///public List<Category> findAllSubCategory(String parentCategoryId);

}
