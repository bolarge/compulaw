package com.congruent.compulaw.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

import com.congruent.compulaw.domain.Caselaw;
import com.congruent.compulaw.domain.Counsel;
import com.congruent.compulaw.domain.Justice;

public interface CaselawRepository extends JpaRepository<Caselaw, Long>{
	
	@Query("select c from Caselaw c where c.caseTitle like :caseTitle and c.judge like :judge and c.counsels like :counsels and c.citations1 like :citations1 and c.citations2 like :citations2 and c.citations3 like :citations3")
	public Page<Caselaw> findCaselawByCriteria(@Param("caseTitle") String caseTitle,
			                               	   @Param("judge") String judge, 
			                                   @Param("counsels") String counsels,
			                                   @Param("citations1") String citations1,
			                               	   @Param("citations2") String citations2, 
			                                   @Param("citations3") String citations3,
			                                   Pageable pageable);
	//
	public List<Caselaw> findByKeywordContaining(String keyword);
	public List<Caselaw> findBycaseTitleOrJudge(String paramString1, String paramString2);
	public List<Caselaw> findBycaseTitleOrJudgeOrCounselsOrCitations1OrCitations2OrCitations3(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, String paramString6);
	public Page<Caselaw> findByKeywordContaining(String keyword, Pageable pageable);
	
	@Query("select c from Caselaw c where c.keyword like :keyword and c.caseTitle like :caseTitle")
	public Page<Caselaw> findCaselawByKeywordCriteria(@Param("keyword")String keyword, @Param("caseTitle")String caseTitle, Pageable pageable);

	@Query("select c from Caselaw c where c.keyword like :keyword")
	public Page<Caselaw> fetchByKeywordCriteria(@Param("keyword")String keyword, Pageable pageable);
	
	@Query("select distinct c.judge from Caselaw c")
	public List<Justice> findAllJudges();
	
	@Query("select distinct c.counsels from Caselaw c where c.judge != null")
	public List<Counsel> findAllCounsels();
	
	@Query("select c from Caselaw c where c.caseTitle like :caseTitle or c.keyword like :keyword")
	public Page<Caselaw> findCaselawByCriteria(@Param("caseTitle") String caseTitle, 
											@Param("keyword") String keyword, Pageable pageable);
	public Page<Caselaw> findBycaseTitleAndKeyword(String caseTitle, String keyword,Pageable pageable );
	


}
