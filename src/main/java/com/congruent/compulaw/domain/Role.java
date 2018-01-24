package com.congruent.compulaw.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;


@Entity
@Table(name="ROLE")
public class Role
  implements Serializable
{
  private static final long serialVersionUID = 1314324262194219402L;
  private String roleName;
  private String description;
  private Set<User> users = new HashSet<User>(0);

  public Role() {
  }
  public Role(String name) {
    this.roleName = name;
  }
  @Id
  @Column(name="ROLENAME", nullable=false, length=15)
  public String getRoleName() {
    return this.roleName;
  }

  public void setRoleName(String roleName) {
    this.roleName = roleName;
  }
  
  @ManyToMany(fetch = FetchType.LAZY, mappedBy = "roles")
	public Set<User> getUsers() {
		return this.users;
	}

  public void setUsers(Set<User> users) {
    this.users = users;
  }

  @Column(name="Description", length=45)
  public String getDescription() {
    return this.description;
  }

  public void setDescription(String description) {
    this.description = description;
  }
  
  public void addUser(User user){
	  users.add(user);	  
  }
}