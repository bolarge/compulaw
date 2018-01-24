package com.congruent.compulaw.serviceImpl;

import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.congruent.compulaw.domain.LawCategory;
import com.congruent.compulaw.domain.LawSubCategory;
import com.congruent.compulaw.repository.LawSubCategoryRepository;
import com.congruent.compulaw.service.LawCategoryService;
import com.congruent.compulaw.service.LawSubCategoryService;
import com.google.common.collect.Lists;

@Service("lawSubCategoryService")
@Repository
@Transactional
public class LawSubCategoryServiceImpl implements LawSubCategoryService{
	
	@Autowired
	LawSubCategoryRepository lawSubCategoryRepository;
	
	@Autowired
	LawCategoryService lawCategoryService;
	
	final Logger logger = LoggerFactory.getLogger(LawSubCategoryServiceImpl.class);

	@Override
	@Transactional(readOnly = true)
	public LawSubCategory findById(Long Id) {
		return this.lawSubCategoryRepository.findOne(Id);
	}

	@Override
	public LawSubCategory save(LawSubCategory lawSubCategory) {	
		Long categoryId = Long.parseLong(lawSubCategory.getCategory());
		LawCategory lc = lawCategoryService.findById(categoryId);
		lawSubCategory.setCategory(null);
		lawSubCategory.setLawCategory(lc);
		return this.lawSubCategoryRepository.save(lawSubCategory);
	}

	@Override
	@Transactional(readOnly = true)
	public List<LawSubCategory> findAll() {
		//logger.info("THis are all THEM :" + lawSubCategoryRepository.findAll().toString());
		return Lists.newArrayList(lawSubCategoryRepository.findAll());
	}

	@Override
	@Transactional(readOnly = true)
	public List<LawSubCategory> findAllSubCategories() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional(readOnly = true)
	public LawSubCategory findByLawSubCategoryId(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(Set<LawSubCategory> theSubs) {
		this.lawSubCategoryRepository.save(theSubs);
		
	}

	@Override
	public Page<LawSubCategory> findAllByPage(Pageable pageable) {
		
		return this.lawSubCategoryRepository.findAll(pageable);
	}

	@Override
	public LawSubCategory findByName(String name) {
		return this.lawSubCategoryRepository.findByName(name);
	}

}
