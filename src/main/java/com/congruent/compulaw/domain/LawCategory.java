package com.congruent.compulaw.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name="LAW_CATEGORY")
public class LawCategory
  implements Serializable
{
  private static final long serialVersionUID = 1L;
  private Long Id;
  private int version = 0;
  //private String code;
  private String name;
  
  private Set<LawSubCategory> theSubCategories = new HashSet<LawSubCategory>();
  //private Set<Caselaw> theCaselaws = new HashSet<Caselaw>();
  //private Set<Act> theActs = new HashSet<Act>();

  public LawCategory() {
  }

  public LawCategory( String name) {
    this.name = name;
  }
  
  public LawCategory(Long id, int version, String name,
		Set<LawSubCategory> theSubCategories) {
	super();
	Id = id;
	this.version = version;
	this.name = name;
	this.theSubCategories = theSubCategories;
}

  @Id
  @GeneratedValue(strategy=GenerationType.AUTO)
  @Column(name="ID")
  public Long getId() {
    return this.Id;
  }
  
  @Column(name="VERSION")
  public int getVersion() {
    return this.version;
  }

  public void setVersion(int version) {
    this.version = version;
  }

  public void setId(Long id) {
    this.Id = id;
  }

  /*@Column(name="CODE")
  public String getCode() {
    return this.code;
  }

  public void setCode(String code) {
    this.code = code;
  }*/

  @Column(name="NAME")
  public String getName()
  {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }
  
  @JsonIgnore
  @OneToMany(fetch=FetchType.LAZY, mappedBy="lawCategory", cascade=CascadeType.ALL, orphanRemoval=true)
  public Set<LawSubCategory> getTheSubCategories() {
    return this.theSubCategories;
  }

  public void setTheSubCategories(Set<LawSubCategory> theSubCategories) {
    this.theSubCategories = theSubCategories;
  }

  public void addLawSubCategory(LawSubCategory lawSubCategory) {	  
    lawSubCategory.setLawCategory(this);
    getTheSubCategories().add(lawSubCategory);												
    
  }

  public void removeLawSubCategory(LawSubCategory lawSubCategory) {
    getTheSubCategories().remove(lawSubCategory);
  }
  
  public void addLawSubCategory(Set<LawSubCategory> theSubs) {
	  ((LawSubCategory) theSubs).setLawCategory(this);
	  getTheSubCategories().addAll(theSubs);		
  }

  /*@JsonBackReference("caselaw-lawCategory")
  @ManyToMany(fetch=FetchType.LAZY, mappedBy="theCategories")
  public Set<Caselaw> getTheCaselaws()
  {
    return this.theCaselaws;
  }

  public void setTheCaselaws(Set<Caselaw> theCaselaws) {
    this.theCaselaws = theCaselaws;
  }*/

  /*@JsonIgnore
  @OneToMany
  @JoinTable(name="ACT_LAWCATEGORY", joinColumns={@javax.persistence.JoinColumn(name="ACT_ID")}, inverseJoinColumns={@javax.persistence.JoinColumn(name="LAWCATEGORY_ID")})
  public Set<Act> getTheActs()
  {
    return this.theActs;
  }

  public void setTheActs(Set<Act> theActs) {
    this.theActs = theActs;
  }*/

  @Override
  public String toString(){
    return "LawCategory [Id=" + this.Id + ", name=" + this.name + 
      "]";
  }

  @Override
  public int hashCode()
  {
    final int prime = 31;
    int result = 1;
    result = prime * result + (this.Id == null ? 0 : this.Id.hashCode());
    
    result = prime * result + (this.name == null ? 0 : this.name.hashCode());
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
    LawCategory other = (LawCategory)obj;
    if (this.Id == null) {
      if (other.Id != null)
        return false;
    } else if (!this.Id.equals(other.Id))
      return false;
    if (this.name == null) {
      if (other.name != null)
        return false;
    } else if (!this.name.equals(other.name))
      return false;
    return true;
  }
}