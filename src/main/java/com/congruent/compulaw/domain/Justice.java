package com.congruent.compulaw.domain;

import java.io.Serializable;

import org.joda.time.DateTime;

public class Justice implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	  private Long id;
	  private int version = 0;
	  private String lastName;
	  private String firstName;
	  private DateTime birthDate;
	  private String sex;
	  private String street;
	  private String city;
	  private String state;
	  private String description;
	  private byte[] photo;
	  
	  public Justice(){}
	  
	  public Justice(String lastName){
		  this.lastName = lastName;
	  }
	  
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public int getVersion() {
		return version;
	}
	public void setVersion(int version) {
		this.version = version;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public DateTime getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(DateTime birthDate) {
		this.birthDate = birthDate;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public byte[] getPhoto() {
		return photo;
	}
	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}
	  

}
