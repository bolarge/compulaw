package com.congruent.compulaw.domain;

import com.congruent.compulaw.enums.SubscriptionType;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.NotEmpty;
import org.joda.time.DateTime;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

@Entity
@Table(name="USER")
public class User
  implements Serializable
{
  private static final long serialVersionUID = 1L;
  private Long id;
  private int version = 0;
  private String lastName;
  private String firstName;
  private DateTime birthDate;
  private String email;
  private String sex;
  private String password;
  private String street;
  private String city;
  private String state;
  private String description;
  private byte[] photo;
  private String userType;
  private boolean enabled = true;
  private SubscriptionType subscriberType;
  private String securityQuestion;
  private String securityAnswer;
 
  //Object Relationship
  private Set<Role> roles = new HashSet<Role>(0);
  private Set<Transaction> transactions = new HashSet<Transaction>(0);

  public User()
  {
  }

  public User(Long id)
  {
    this.id = id;
  }

  public User(String lastName, String firstName, DateTime birthDate)
  {
    this.lastName = lastName;
    this.firstName = firstName;
    this.birthDate = birthDate;
  }

  public User(Long id, Integer version, String lastName, String firstName, String email, String sex, String password, String street, String city, String state, DateTime birthDate, String description, byte[] photo, String userType, boolean enabled, Set<Role> roles)
  {
    this.id = id;
    this.version = version.intValue();
    this.lastName = lastName;
    this.firstName = firstName;
    this.email = email;
    this.sex = sex;
    this.password = password;
    this.street = street;
    this.city = city;
    this.state = state;
    this.birthDate = birthDate;
    this.description = description;
    this.photo = photo;
    this.userType = userType;
    this.enabled = enabled;
    this.roles = roles;
  }
  
  @Id
  @GeneratedValue(strategy=GenerationType.AUTO)
  @Column(name="USER_ID", unique=true, nullable=false)
  public Long getId() { return this.id; }

  public void setId(Long id)
  {
    this.id = id;
  }
  @Version
  @Column(name="VERSION", nullable=false)
  public int getVersion() {
    return this.version;
  }

  public void setVersion(int version) {
    this.version = version;
  }
  @NotEmpty(message="{validation.lastname.NotEmpty.message}")
  @Size(min=3, max=15, message="{validation.lastname.Size.message}")
  @Column(name="LAST_NAME", length=15)
  public String getLastName() { return this.lastName; }

  public void setLastName(String lastName)
  {
    this.lastName = lastName;
  }

  @NotEmpty(message="{validation.lastname.NotEmpty.message}")
  @Size(min=3, max=15, message="{validation.firstname.Size.message}")
  @Column(name="FIRST_NAME", length=15)
  public String getFirstName()
  {
    return this.firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }
  @Column(name="BIRTH_DATE")
  @Type(type="org.jadira.usertype.dateandtime.joda.PersistentDateTime")
  @DateTimeFormat(iso=ISO.DATE)
  public DateTime getBirthDate() { return this.birthDate; }

  public void setBirthDate(DateTime birthDate)
  {
    this.birthDate = birthDate; } 
  
  @NotEmpty(message="{validation.email.NotEmpty.message}")
  @NotNull
  @Size(min=10, max=45, message="{validation.email.Size.message}")
  @Column(name="EMAIL", unique=true, nullable=false, length=45)
  public String getEmail() { return this.email; }

  public void setEmail(String email)
  {
    this.email = email;
  }

  @Column(name="SEX", length=5)
  public String getSex() {
    return this.sex;
  }

  public void setSex(String sex) {
    this.sex = sex;
  }

  @Column(name="PASSWORD", length=100)
  public String getPassword()
  {
    return this.password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  @Column(name="STREET", length=15)
  public String getStreet() {
    return this.street;
  }

  public void setStreet(String street) {
    this.street = street;
  }

  @Column(name="CITY", length=15)
  public String getCity() {
    return this.city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  @Column(name="STATE", length=15)
  public String getState() {
    return this.state;
  }

  public void setState(String state) {
    this.state = state;
  }

  @Column(name="DESCRIPTION", length=45)
  public String getDescription() {
    return this.description;
  }

  public void setDescription(String description) {
    this.description = description;
  }
  @Basic(fetch=FetchType.LAZY)
  @Lob
  @Column(name="PHOTO")
  public byte[] getPhoto() { return this.photo; }

  public void setPhoto(byte[] photo)
  {
    this.photo = photo;
  }

  @Column(name="ENABLED")
  public boolean isEnabled() {
    return this.enabled;
  }

  public void setEnabled(boolean enabled) {
    this.enabled = enabled;
  }

  @Transient
  public String getBirthDateString()
  {
    String birthDateString = "";
    if (this.birthDate != null)
      birthDateString = org.joda.time.format.DateTimeFormat.forPattern(
        "yyyy-MM-dd").print(this.birthDate);
    return birthDateString;
  }

  public String toString()
  {
    return "User - Id: " + this.id + ", First name: " + this.firstName + 
      ", Last name: " + this.lastName + ", Birthday: " + this.birthDate + 
      ", Description: " + this.description;
  }

  @Column(name="USER_TYPE", length=5)
  public String getUserType() {
    return this.userType;
  }

  public void setUserType(String userType) {
    this.userType = userType;
  }

  @Column(name="SUBSCR_TYPE", length=5)
  public SubscriptionType getSubscriberType() {
    return this.subscriberType;
  }

  public void setSubscriberType(SubscriptionType subscriberType) {
    this.subscriberType = subscriberType;
  }

  @Column(name="QUESTION", length=25)
  public String getSecurityQuestion() {
    return this.securityQuestion;
  }

  public void setSecurityQuestion(String securityQuestion) {
    this.securityQuestion = securityQuestion;
  }

  @Column(name="ANSWER", length=25)
  public String getSecurityAnswer() {
    return this.securityAnswer;
  }

  public void setSecurityAnswer(String securityAnswer) {
    this.securityAnswer = securityAnswer;
  }
  
  @Override
public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((email == null) ? 0 : email.hashCode());
	result = prime * result + ((id == null) ? 0 : id.hashCode());
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
	User other = (User) obj;
	if (email == null) {
		if (other.email != null)
			return false;
	} else if (!email.equals(other.email))
		return false;
	if (id == null) {
		if (other.id != null)
			return false;
	} else if (!id.equals(other.id))
		return false;
	return true;
}

@JsonIgnore
@ManyToMany(fetch = FetchType.EAGER)
@JoinTable(name = "user_role_detail",
           joinColumns = @JoinColumn(name = "USER_ID"), 
           inverseJoinColumns = @JoinColumn(name = "ROLE_ID")) 
  public Set<Role> getRoles() {
    return this.roles;
  }

  public void setRoles(Set<Role> roles) {
    this.roles = roles;
  }
  
  public void addRole(Role role){
	  roles.add(role);	  
  }

  @JsonIgnore
  @OneToMany(mappedBy="user")
  public Set<Transaction> getTransactions() {
    return this.transactions;
  }

  public void setTransactions(Set<Transaction> transactions) {
    this.transactions = transactions;
  }
}