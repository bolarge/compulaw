package com.congruent.compulaw.serviceImpl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.congruent.compulaw.domain.Document;
import com.congruent.compulaw.repository.DocumentRepository;
import com.congruent.compulaw.service.DocumentService;

@Service("documentService")
@Repository
@Transactional
public class DocumentServiceImpl implements DocumentService {
	
	@Autowired
	private DocumentRepository documentRepository;

	@Override
	@Transactional(readOnly=true)
	public Document findById(Long id) {
		return documentRepository.findOne(id);
	}

	
}
