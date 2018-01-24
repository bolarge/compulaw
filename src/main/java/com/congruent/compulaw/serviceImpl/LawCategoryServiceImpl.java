package com.congruent.compulaw.serviceImpl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.congruent.compulaw.domain.LawCategory;
import com.congruent.compulaw.domain.LawSubCategory;
import com.congruent.compulaw.repository.LawCategoryRepository;
import com.congruent.compulaw.service.LawCategoryService;
import com.google.common.collect.Lists;

@Service("lawCategoryService")
@Repository
@Transactional
public class LawCategoryServiceImpl implements LawCategoryService{

	@Autowired
	LawCategoryRepository lawCategoryRepository;
	
	final Logger logger = LoggerFactory.getLogger(LawCategoryServiceImpl.class);
	
	@Override
	@Transactional(readOnly = true)
	public LawCategory findById(Long Id) {	
		return lawCategoryRepository.findOne(Id);
	}

	@Override
	public LawCategory save(LawCategory lawCategory) {
		return lawCategoryRepository.save(lawCategory);
	}

	@Override
	@Transactional(readOnly = true)
	public List<LawCategory> findAll() {
		return Lists.newArrayList(lawCategoryRepository.findAll());
	}

	@Override
	@Transactional(readOnly = true)
	public List<LawSubCategory> findAllSubCategories() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional(readOnly = true)
	public Page<LawCategory> findAllByPage(PageRequest pageRequest) {
		return lawCategoryRepository.findAll(pageRequest);
	}

}
