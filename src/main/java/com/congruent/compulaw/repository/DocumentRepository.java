package com.congruent.compulaw.repository;

import org.springframework.data.repository.CrudRepository;

import com.congruent.compulaw.domain.Document;

public interface DocumentRepository extends CrudRepository<Document, Long>{

}
