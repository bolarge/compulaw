package com.congruent.compulaw.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.congruent.compulaw.domain.Caselaw;
import com.congruent.compulaw.domain.CaselawSearchCriteria;
import com.congruent.compulaw.domain.Counsel;
import com.congruent.compulaw.domain.Justice;

public interface CaselawService {

	// Find a transaction by id
	public Caselaw findById(Long id);
	public List<Justice> getAllJustice();
	public List<Counsel> getAllCounsel();
	public Caselaw save(Caselaw caselaw);
	public void delete(Caselaw caselaw);
	//	
	public Page<Caselaw> findBycaseTitleAndKeyword(CaselawSearchCriteria criteria, Pageable pageable);
	public Page<Caselaw> findAllByPage(Pageable pageable);
	public Page<Caselaw> findByPage(Pageable pageable);
	public Page<Caselaw> findCaselawByCriteria(CaselawSearchCriteria searchCriteria, Pageable pageable);
	public Page<Caselaw> findCaselawByKeyword(CaselawSearchCriteria searchCriteria, Pageable pageable);
	public Page<Caselaw> getKeywordSearchResult(String keyword, Integer pageNumber);
	//
	//public List<Caselaw> findAll();
	public List<Caselaw> findAll();
	public List<Caselaw> findRandom(int count);	
	public List<Caselaw> findBycaseTitleAndJudgeAndCounselsAndCitations1AndCitations2AndCitations3(CaselawSearchCriteria criteria);
	public List<Caselaw> findBycaseTitleOrJudgeOrCounselsOrCitations1OrCitations2OrCitations3(CaselawSearchCriteria criteria);		
	public List<String> selectCaseTitle();
	public List<Caselaw> findByKeyword(String keyword);
}
