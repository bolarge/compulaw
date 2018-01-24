package com.congruent.compulaw.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.congruent.compulaw.domain.Act;
import com.congruent.compulaw.domain.ActSearchCriteria;
import com.congruent.compulaw.domain.ActSummary;

public interface ActService {

	public Act findById(Long id);
	public Act save(Act act);
	public List<Act> findAll();
	public Page<Act> findAllByPage(Pageable pageable);
	public List<ActSummary> listAll(Pageable pageable);
	public Page<Act> findAllByDocumentFileData(Pageable pageable);
	//public List<Act> findByTitleOrSection1OrSection2OrSection3OrSection4OrSection5(ActSearchCriteria criteria);
	public Page<Act> findActByCriteria(ActSearchCriteria searchCriteria, PageRequest pageRequest);
	
}
