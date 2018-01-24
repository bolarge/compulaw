package com.congruent.compulaw.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.congruent.compulaw.domain.LawCategory;

public interface LawCategoryRepository extends JpaRepository<LawCategory, Long>{

	//public List<LawSubCategory> findAllSubCategories();
	//public LawCategory findBySubCategoryId(Long id);
	//public LawSubCategory findBySubCategory(Long LawSubCategoryId);
}
