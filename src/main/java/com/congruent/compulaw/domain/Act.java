package com.congruent.compulaw.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.AttributeOverride;
import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

//import org.codehaus.jackson.annotate.JsonIgnore;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table (name = "ACTS")
@NamedQueries({
@NamedQuery(name="Act.findAll", query="select a from Act a"),
@NamedQuery(name="Act.findById", query="select distinct a from Act a left join fetch a.document d where a.id = :id"),
@NamedQuery(name="Act.findAllWithDetail", query="select distinct a from Act a left join fetch a.document d")})
public class Act implements Serializable{ 
	
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private int version = 0;
	private String title;
	private String fileName;
	private String sectionTitle;
	private String section1;
	private String section2;
	private String section3;
	private String section4;
	private String parentId;
	
	//Architecture Relationship 
	private Document document;			
	private Set<ActSection> theSections = new HashSet<ActSection>();
	
	public Act() {
		super();
		//document = new Document();
	}
		
	public Act(String title, String fileName, String sectionTitle,
			Document document, Set<ActSection> theSections) {
		super();
		this.title = title;
		this.fileName = fileName;
		this.sectionTitle = sectionTitle;
		this.document = document;
		this.theSections = theSections;
	}
	
	public Act(String title, Long Id){
		this.title = title;
		this.id = Id;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ID")
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	@Column(name="VERSION", nullable = false)
	public int getVersion() {
		return version;
	}
	
	public void setVersion(int version) {
		this.version = version;
	}
	
	@Column(name="TITLE",unique=true,nullable=false)
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}

	//bi-directional one-to-one association to DocumentRepository
	@JsonIgnore
	//@JsonManagedReference("act-document")
	@OneToOne(fetch=FetchType.LAZY ,cascade=CascadeType.ALL)
	@JoinColumn(name="DOCUMENT_ID")
	public Document getDocument() {
		return this.document;
	}
	
	public void setDocument(Document document) {
		this.document = document;
	}
	
	@JsonIgnore
	//@JsonManagedReference("act-section")
	@ElementCollection
	@CollectionTable(
			name="ACT_SECTION",
			joinColumns=@JoinColumn(name="ACT_ID"))
	@AttributeOverride(name="sectionTitle",
	column=@Column(name="SECTIONS",unique=true,nullable=false))
	public Set<ActSection> getTheSections() {
		return theSections;
	}
	
	public void setTheSections(Set<ActSection> theSections) {
		this.theSections = theSections;
	}
	
	/*@ManyToOne
	@JoinColumn(name = "LAWCATEGORY_ID")
	public LawCategory getLawCategory() {
		return lawCategory;
	}

	public void setLawCategory(LawCategory lawCategory) {
		this.lawCategory = lawCategory;
	}*/

	@Transient
	//@Column(name="FILENAME",unique=true)
	public String getFileName() {
		return fileName;
	}
	
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	@Transient
	public String getSectionTitle() {
		return sectionTitle;
	}

	public void setSectionTitle(String sectionTitle) {
		this.sectionTitle = sectionTitle;
	}

	@Transient
	public String getSection1() {
		return section1;
	}

	public void setSection1(String section1) {
		this.section1 = section1;
	}

	@Transient
	public String getSection2() {
		return section2;
	}

	public void setSection2(String section2) {
		this.section2 = section2;
	}

	@Transient
	public String getSection3() {
		return section3;
	}

	public void setSection3(String section3) {
		this.section3 = section3;
	}

	@Transient
	public String getSection4() {
		return section4;
	}

	public void setSection4(String section4) {
		this.section4 = section4;
	}

	

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	@Override
	public String toString() {
		return "Act [id=" + id + ", title=" + title + ", document="
				+ document + "]";
	}	
}
