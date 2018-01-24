package com.congruent.compulaw.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.congruent.compulaw.domain.LawSubCategory;

public interface LawSubCategoryRepository extends PagingAndSortingRepository<LawSubCategory, Long>{

	public LawSubCategory findByName(String name);

	//@Query("select c from Category c where c.parentCategory is null")
	//public List<Category> findAllParentCategory();
	
	//@Query("select c from Category c where c.parentCategory.categoryId = :parentCategoryId")
	//List<Category> findAllSubCategory(@Param("parentCategoryId") String parentCategoryId);
}

