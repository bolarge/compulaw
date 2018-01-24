package com.congruent.compulaw.domain;

import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "LAW_SUB_CATEGORY")
public class LawSubCategory implements Serializable, Comparable<LawSubCategory> {
	
	private static final long serialVersionUID = 1L;
	private Long Id;
	private String name;
	private String category;
	
	//Parent to a Law Sub-Category
	private LawCategory lawCategory;	
	private Set<Caselaw> caselaws = new HashSet<Caselaw>();

	public LawSubCategory() {}

	public LawSubCategory(String name) {
		this.name = name;
	}

	public LawSubCategory(String name, LawCategory category) {
		this.name = name;
		this.lawCategory = category;
	}

	public LawSubCategory(Long id, String name) {
		this.Id = id;
		this.name = name;
	}

	public LawSubCategory(Long id) {
		this.Id = id;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	public Long getId() {
		return this.Id;
	}

	public void setId(Long id) {
		this.Id = id;
	}

	@Column(name = "NAME")
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "LAWCATEGORY_ID")
	public LawCategory getLawCategory() {
		return this.lawCategory;
	}

	public void setLawCategory(LawCategory aCategory) {
		this.lawCategory = aCategory;
	}

	@Transient
	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	//@JsonBackReference("caselaw-lawSubCategory")
	@JsonIgnore
	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "theSubCategories")
	public Set<Caselaw> getCaselaws() {
		return this.caselaws;
	}

	public void setCaselaws(Set<Caselaw> caselaws) {
		this.caselaws = caselaws;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (this.Id == null ? 0 : this.Id.hashCode());
		result = prime * result
				+ (this.name == null ? 0 : this.name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LawSubCategory other = (LawSubCategory) obj;
		if (this.Id == null) {
			if (other.Id != null)
				return false;
		} else if (!this.Id.equals(other.Id)) {
			return false;
		}

		if (this.name == null) {
			if (other.name != null)
				return false;
		} else if (!this.name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "LawSubCategory [Id=" + this.Id + ", name=" + this.name + "]";
	}

	@Override
	public int compareTo(LawSubCategory o) {
		int compareQuantity = ((LawSubCategory) o).getId().intValue(); 
		 
		//ascending order
		return this.Id.intValue() - compareQuantity;
 
		//descending order
		//return compareQuantity - this.quantity;
	}
	public static Comparator<LawSubCategory> FruitNameComparator = new Comparator<LawSubCategory>() {

		public int compare(LawSubCategory cat1, LawSubCategory cat2) {

			String catName1 = cat1.getName();
			String catName2 = cat2.getName(); //.toUpperCase();

			//ascending order
			return catName1.compareTo(catName2);

			//descending order
			//return fruitName2.compareTo(fruitName1);
		}

	};
}