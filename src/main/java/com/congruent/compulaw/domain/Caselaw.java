package com.congruent.compulaw.domain;

import java.io.Serializable;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.Version;

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="CASELAW")
public class Caselaw implements Serializable, Comparable<Caselaw>{
	
  private static final long serialVersionUID = 1L;
  
  private Long id; 
  private String caseFile;
  private String caseTitle;
  private String citations1;
  private String citations2;
  private String citations3;
  private String counsels;
  private DateTime created;
  private String fileName;
  private String judge;  
  private int version = 0;  
  private String keyword;
  private DateTime releasedDate;
  private String subCategory;
  private String year;
  
  //Object Architecture
  //private List<LawCategory> lawCategory = new ArrayList<LawCategory>();
  private Document caseLawDocument;
  //private Set<LawCategory> theCategories = new HashSet<LawCategory>();
  private Set<LawSubCategory> theSubCategories = new HashSet<LawSubCategory>(0);

  public Caselaw(){}

  public Caselaw(Long id, String caseFile, String caseTitle, String judge, String counsels, String citations1, String citations2, String citations3){
    this.id = id;
    this.caseFile = caseFile;
    this.caseTitle = caseTitle;
    this.judge = judge;
    this.counsels = counsels;
    this.citations1 = citations1;
    this.citations2 = citations2;
    this.citations3 = citations3;
  }

  public Caselaw(Long id, String caseTitle, String judge, String counsels, String citations1, String citations2, String citations3, DateTime caselawDate, String keyword, Document caseLawDocument){
    this.id = id;
    this.caseTitle = caseTitle;
    this.judge = judge;
    this.counsels = counsels;
    this.citations1 = citations1;
    this.citations2 = citations2;
    this.citations3 = citations3;
    //this.created = created;
    this.releasedDate = caselawDate;
    this.keyword = keyword;
    this.caseLawDocument = caseLawDocument;
  }
  
  @Id
  @GeneratedValue(strategy=GenerationType.AUTO)
  @Column(name="ID")
  public Long getId() {
    return this.id;
  }

  public void setId(Long id) {
    this.id = id;
  }
  @Version
  @Column(name="VERSION")
  public int getVersion() {
    return this.version;
  }

  public void setVersion(int version) {
    this.version = version;
  }

  @Column(name="CASE_TITLE")
  public String getCaseTitle()
  {
    return this.caseTitle;
  }

  public void setCaseTitle(String caseTitle) {
    this.caseTitle = caseTitle;
  }

  @Column(name="JUDGE")
  public String getJudge()
  {
    return this.judge;
  }

  public void setJudge(String judge) {
    this.judge = judge;
  }

  @Column(name="COUNSELS")
  public String getCounsels()
  {
    return this.counsels;
  }

  public void setCounsels(String counsels) {
    this.counsels = counsels;
  }

  @Column(name="CITATIONS1")
  public String getCitations1()
  {
    return this.citations1;
  }

  public void setCitations1(String citations1) {
    this.citations1 = citations1;
  }

  @Column(name="CITATIONS2")
  public String getCitations2()
  {
    return this.citations2;
  }

  public void setCitations2(String citations2) {
    this.citations2 = citations2;
  }

  @Column(name="CITATIONS3")
  public String getCitations3()
  {
    return this.citations3;
  }

  public void setCitations3(String citations3) {
    this.citations3 = citations3;
  }

  @Transient
  public String getFileName() {
    return this.fileName;
  }

  public void setFileName(String fileName) {
    this.fileName = fileName;
  }
  @Column(name="CREATED")
  @Type(type="org.jadira.usertype.dateandtime.joda.PersistentDateTime")
  @org.springframework.format.annotation.DateTimeFormat(iso=DateTimeFormat.ISO.DATE)
  public DateTime getCreated() { return this.created; }

  public void setCreated(DateTime created)
  {
    this.created = created;
  }

  @Column(name="KEYWORD", length=1000)
  public String getKeyword() {
    return this.keyword;
  }

  public void setKeyword(String keyword) {
    this.keyword = keyword;
  }

  @Transient
  public String getReleasedDateString() {
    String releasedDateString = "";
    if (this.releasedDate != null)
    	releasedDateString = org.joda.time.format.DateTimeFormat.forPattern(
        "dd-MM-yyyy").print(this.releasedDate);
    return releasedDateString;
  }

  /*@Transient
  public String getCreatedDateString() {
    String createdDateString = "";
    if (this.created != null)
      createdDateString = org.joda.time.format.DateTimeFormat.forPattern(
        "dd-MM-yyyy").print(this.created);
    return createdDateString;
  }*/

  @Type(type="org.jadira.usertype.dateandtime.joda.PersistentDateTime")
  @org.springframework.format.annotation.DateTimeFormat(iso=DateTimeFormat.ISO.DATE)
  @Column(name="RELEASED_DATE")
  public DateTime getReleasedDate() {
    return this.releasedDate;
  }

  public void setReleasedDate(DateTime releasedDate) {
    this.releasedDate = releasedDate;
  }

  @Column(name="CASE_FILE")
  public String getCaseFile()
  {
    return this.caseFile;
  }

  public void setCaseFile(String caseFile) {
    this.caseFile = caseFile;
  }

  @Column(name="SUB_CATEGORY",length=1000)
  public String getSubCategory() {
	return subCategory;
  }

  public void setSubCategory(String subCategory) {
	this.subCategory = subCategory;
  }

  @Column(name="YEAR")
  public String getYear() {
	return year;
  }

  public void setYear(String year) {
	this.year = year;
  }

/*@Transient
  public List<LawCategory> getLawCategory() {
    return this.lawCategory;
  }

  public void setLawCategory(List<LawCategory> lawCategory) {
    this.lawCategory = lawCategory;
  }*/
  
  @JsonIgnore
  @OneToOne(fetch=FetchType.LAZY, cascade=CascadeType.ALL, orphanRemoval=true)
  @JoinColumn(name="DOCUMENT_ID")
  public Document getCaseLawDocument() {
    return this.caseLawDocument;
  }

  public void setCaseLawDocument(Document document) {
    this.caseLawDocument = document;
  }

  /*@ManyToMany(fetch=FetchType.LAZY)
  @JoinTable(name="CASELAW_LAWCATEGORY", joinColumns={@JoinColumn(name="CASELAW_ID")}, inverseJoinColumns={@JoinColumn(name="LAWCATEGORY_ID")})
  @JsonIgnore
  public Set<LawCategory> getTheCategories()
  {
    return this.theCategories;
  }

  public void setTheCategories(Set<LawCategory> theCategories) {
    this.theCategories = theCategories;
  }*/

  @ManyToMany(fetch=FetchType.LAZY)
  @JoinTable(name="CASELAW_SUB_LAWCATEGORY", joinColumns={@JoinColumn(name="CASELAW_ID")}, inverseJoinColumns={@JoinColumn(name="CATEGORYSUB_ID")})
  @JsonIgnore
  //@OrderBy("name DESC")
  public Set<LawSubCategory> getTheSubCategories(){
    return this.theSubCategories;
  }

  public void setTheSubCategories(Set<LawSubCategory> theSubCategories) {
    this.theSubCategories = theSubCategories;
  }

  @Override
  public int hashCode(){
    final int prime = 31;
    int result = 1;
    result = prime * result + (this.caseTitle == null ? 0 : this.caseTitle.hashCode());
    result = prime * result + (this.id == null ? 0 : this.id.hashCode());
    result = prime * result + this.version;
    return result;
  }

  @Override
  public boolean equals(Object obj)
  {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    Caselaw other = (Caselaw)obj;
    if (this.caseTitle == null) {
      if (other.caseTitle != null)
        return false;
    } else if (!this.caseTitle.equals(other.caseTitle))
      return false;
    if (this.id == null) {
      if (other.id != null)
        return false;
    } else if (!this.id.equals(other.id))
      return false;
    if (this.version != other.version)
      return false;
    return true;
  }

  @Override
  public String toString()
  {
    return "Caselaw [getCaseTitle()=" + 
      getCaseTitle() + ", getJudge()=" + getJudge() + 
      ", getCounsels()=" + getCounsels() + ", getCitations1()=" + 
      getCitations1() + "]";
  }

  @Override
  public int compareTo(Caselaw o) {
	int caseId = ((Caselaw) o).getId().intValue(); 
	 
	//ascending order
	return this.id.intValue() - caseId;

	//descending order
	//return compareQuantity - this.quantity
  }
  
  public static Comparator<Caselaw> CaselawTitleComparator = new Comparator<Caselaw>() {

		public int compare(Caselaw cat1, Caselaw cat2) {

			String catName1 = cat1.getCaseTitle();
			String catName2 = cat2.getCaseTitle(); //.toUpperCase();

			//ascending order
			return catName1.compareTo(catName2);

			//descending order
			//return fruitName2.compareTo(fruitName1);
		}

	};

}