package com.congruent.compulaw.serviceImpl;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.congruent.compulaw.domain.Caselaw;
import com.congruent.compulaw.domain.CaselawSearchCriteria;
import com.congruent.compulaw.domain.Counsel;
import com.congruent.compulaw.domain.Justice;
import com.congruent.compulaw.domain.LawSubCategory;
import com.congruent.compulaw.repository.CaselawRepository;
import com.congruent.compulaw.service.CaselawService;
import com.congruent.compulaw.service.LawSubCategoryService;
import com.google.common.collect.Lists;

@Service("caselawService")
@Repository
@Transactional
public class CaselawServiceImpl implements CaselawService{
	
	@Autowired
	CaselawRepository caselawRepository;
	
	@Autowired
	LawSubCategoryService lawSubCategoryService;
	
	final Logger logger = LoggerFactory.getLogger(CaselawServiceImpl.class);
	
	 private static final int PAGE_SIZE = 15;

	@Override
	@Transactional(readOnly = true)
	public Caselaw findById(Long id) {
		return caselawRepository.findOne(id);
	}

	@Override
	public Caselaw save(Caselaw caselaw) {		
			//logger.info("Print the Sub categories out " + caselaw.getTheSubCategories().toString());
			
			Set<LawSubCategory> caselawSubCats = new HashSet<LawSubCategory>();
			//
			Iterator<LawSubCategory> subsIter = caselaw.getTheSubCategories().iterator();
			while (subsIter.hasNext()){
				LawSubCategory lsc = subsIter.next();
				if(lsc.getName() != null){
					logger.info("Print before Id obtained out " + lsc.getName());
					LawSubCategory getItId =  lawSubCategoryService.findById(Long.parseLong(lsc.getName()));
					logger.info("Print after Id obtained out " + getItId.toString());
					caselawSubCats.add(getItId);
				}
			}
			caselaw.setTheSubCategories(caselawSubCats);
			return this.caselawRepository.save(caselaw);		
	}

	@Override
	@Transactional(readOnly = true)
	public List<Caselaw> findAll() {
		return Lists.newArrayList(caselawRepository.findAll());
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Caselaw> findAllByPage(Pageable pageable) {	
		return caselawRepository.findAll(pageable);
	}
	
	@Override
	@Transactional(readOnly = true)
	public Page<Caselaw> findByPage(Pageable pageable) {
		logger.info("These are the selections  " + caselawRepository.findAll(pageable));
		return caselawRepository.findAll(pageable);
	}

	@Override
	public List<Caselaw> findRandom(int count) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<Caselaw> findBycaseTitleOrJudgeOrCounselsOrCitations1OrCitations2OrCitations3(
			CaselawSearchCriteria criteria) {	
		return Lists.newArrayList(caselawRepository.findBycaseTitleOrJudgeOrCounselsOrCitations1OrCitations2OrCitations3(criteria.getCaseTitle(), 
				criteria.getJudge(), criteria.getCounsels(), criteria.getCitations1(), criteria.getCitations2(), criteria.getCitations3()));
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Caselaw> findBycaseTitleAndKeyword(CaselawSearchCriteria criteria, Pageable pageable) {
		return caselawRepository.findBycaseTitleAndKeyword(criteria.getCaseTitle(), criteria.getKeyword(), pageable);
	
	}

	@Override
	public void delete(Caselaw caselaw) {
		// TODO Auto-generated method stub	
	}

	@Override
	@Transactional(readOnly=true)
	public Page<Caselaw> findCaselawByCriteria(CaselawSearchCriteria searchCriteria, Pageable pageable) {
		String caseTitle = searchCriteria.getCaseTitle();
		String judge = searchCriteria.getJudge();
		String counsels = searchCriteria.getCounsels();
		String citations1 = searchCriteria.getCitations1();
		String citations2 = searchCriteria.getCitations2();
		String citations3 = searchCriteria.getCitations3();
		//String caseFile = searchCriteria.getCaseFile();
		//String keyword = searchCriteria.getKeyword(); keyword,
		return caselawRepository.findCaselawByCriteria(caseTitle, judge, counsels, citations1, citations2, citations3, pageable);
	}

	@Override
	@Transactional(readOnly = true)
	public List<String> selectCaseTitle() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional(readOnly=true)
	public Page<Caselaw> findCaselawByKeyword(CaselawSearchCriteria searchCriteria, Pageable request) {
		String keyword = searchCriteria.getKeyword();
		logger.info("Keyword in service Imp is : " + keyword);
		String caseTitle = searchCriteria.getCaseTitle();
		logger.info("caseTitle in service Imp is : " + caseTitle);
		//return caselawRepository.findCaselawByKeywordCriteria(keyword, caseTitle, request);
		return caselawRepository.findCaselawByCriteria(caseTitle, keyword, request);
		
	}

	//Keyword Search Implementation
	@Override
	@Transactional(readOnly=true)
	public List<Caselaw> findByKeyword(String keyword) {
		return caselawRepository.findByKeywordContaining(keyword);
	}

	//Keyword Pagination Support Implementation
	@Override
	@Transactional(readOnly=true)
	public Page<Caselaw> getKeywordSearchResult(String keyword, Integer pageNumber) {
		 PageRequest request = new PageRequest(pageNumber - 1, PAGE_SIZE, Sort.Direction.DESC, "caseTitle");
		 //return caselawRepository.findAll(request);
		 return caselawRepository.findByKeywordContaining(keyword, request);
	}

	@Override
	public List<Justice> getAllJustice() {
		//logger.info("Justices are as: " + caselawRepository.findAllJudges());
		return caselawRepository.findAllJudges();
	}

	@Override
	public List<Counsel> getAllCounsel() {
		logger.info("Counsels are as: " + caselawRepository.findAllCounsels());
		return caselawRepository.findAllCounsels();
	}

	@Override
	public List<Caselaw> findBycaseTitleAndJudgeAndCounselsAndCitations1AndCitations2AndCitations3(
			CaselawSearchCriteria criteria) {
		// TODO Auto-generated method stub
		return null;
	}	
}
