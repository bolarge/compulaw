package com.congruent.compulaw.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.congruent.compulaw.domain.Act;

public interface ActRepository extends PagingAndSortingRepository<Act, Long>{

	@Query("select a from Act a where a.title like :title ")
	public Page<Act> findActByCriteria(@Param("title") String title, Pageable pageable);	
	public Page<Act> findAllByDocumentFileData(Pageable pageable);
	public Page<Act> findAllByDocument(Pageable pageable);
}
