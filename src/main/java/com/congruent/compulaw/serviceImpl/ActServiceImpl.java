package com.congruent.compulaw.serviceImpl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.congruent.compulaw.domain.Act;
import com.congruent.compulaw.domain.ActSearchCriteria;
import com.congruent.compulaw.domain.ActSummary;
import com.congruent.compulaw.repository.ActRepository;
import com.congruent.compulaw.service.ActService;
import com.google.common.collect.Lists;

@Service("actService")
@Repository
@Transactional
public class ActServiceImpl implements ActService {

	@Autowired
	ActRepository actRepository;

	// Log events
	final Logger logger = LoggerFactory.getLogger(ActServiceImpl.class);

	@Override
	@Transactional(readOnly = true)
	public Act findById(Long id) {	
		return actRepository.findOne(id);
	}

	@Override
	public Act save(Act act) {		
		return actRepository.save(act);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Act> findAll() {		
		return Lists.newArrayList(actRepository.findAll());
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Act> findAllByPage(Pageable pageable) {
		return actRepository.findAll(pageable);
	}

	@Override
	public List<ActSummary> listAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<Act> findAllByDocumentFileData(Pageable pageable) {
		logger.info("These are them :" + actRepository.findAllByDocumentFileData(pageable).toString());
		return actRepository.findAllByDocumentFileData(pageable);
	}

	
	public Page<Act> findActByCriteria(ActSearchCriteria searchCriteria,PageRequest pageRequest) {
		String title = searchCriteria.getTitle();
		return actRepository.findActByCriteria(title, pageRequest);
	}
}
