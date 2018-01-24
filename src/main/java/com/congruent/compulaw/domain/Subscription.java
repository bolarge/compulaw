package com.congruent.compulaw.domain;

import com.congruent.compulaw.enums.SubscriptionType;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="SUBSCRIPTION_ITEM")
public class Subscription
  implements Serializable
{
  private static final long serialVersionUID = 1L;
  private Long id;
  private String itemName;
  private String duration;
  private String price;
  private int version = 0;
  private SubscriptionType subscriptionPlan;
  
  //private Set<UserSubscription> userSubscriptions = new HashSet<UserSubscription>();
  private Set<Transaction> transactions = new HashSet<Transaction>(0);

  public Subscription()
  {
  }

  public Subscription(Long id)
  {
    this.id = id;
  }

  public Subscription(String itemName) {
    this.itemName = itemName;
  }

  public Subscription(Long id, String itemName, String duration, String price, int version)
  {
    this.id = id;
    this.itemName = itemName;
    this.duration = duration;
    this.price = price;
    this.version = version;
    //this.userSubscriptions = userSubscriptions;
  }

  public Subscription(Long id, String itemName, String duration, String price, Set<Transaction> transactions)
  {
    this.id = id;
    this.itemName = itemName;
    this.duration = duration;
    this.price = price;
    this.transactions = transactions;
  }
  @Id
  @GeneratedValue(strategy=GenerationType.AUTO)
  @Column(name="ID", nullable=false)
  public Long getId() { return this.id; }

  public void setId(Long id)
  {
    this.id = id;
  }

  @Column(name="ITEM_NAME", nullable=false, unique=true, length=25)
  public String getItemName() {
    return this.itemName;
  }

  public void setItemName(String itemName) {
    this.itemName = itemName;
  }

  @Column(name="DURATION", nullable=false, length=25)
  public String getDuration() {
    return this.duration;
  }

  public void setDuration(String duration) {
    this.duration = duration;
  }

  @Column(name="PRICE", nullable=false, length=10)
  public String getPrice() {
    return this.price;
  }

  public void setPrice(String price) {
    this.price = price;
  }

  @Column(name="VERSION")
  public int getVersion() {
    return this.version;
  }

  public void setVersion(int version) {
    this.version = version;
  }
  @Column(name="DURATION2")
  @Enumerated(EnumType.STRING)
  public SubscriptionType getSubscriptionPlan() {
    return this.subscriptionPlan;
  }

  public void setSubscriptionPlan(SubscriptionType subscriptionPlan) {
    this.subscriptionPlan = subscriptionPlan;
  }
  
  /*@JsonIgnore
  @OneToMany(fetch=FetchType.LAZY, mappedBy="pk.subscription", cascade={javax.persistence.CascadeType.ALL})
  public Set<UserSubscription> getUserSubscriptions() {
    return this.userSubscriptions;
  }

  public void setUserSubscriptions(Set<UserSubscription> userSubscriptions) {
    this.userSubscriptions = userSubscriptions;
  }*/
  
  //@JsonIgnore
  @JsonIgnore
  @OneToMany(mappedBy="subscription")
  public Set<Transaction> getTransactions() {
    return this.transactions;
  }

  public void setTransactions(Set<Transaction> transactions) {
    this.transactions = transactions;
  }

  public int hashCode()
  {
	final int prime = 31;
    int result = 1;
    result = prime * result + (this.id == null ? 0 : this.id.hashCode());
    result = prime * result + (this.itemName == null ? 0 : this.itemName.hashCode());
    return result;
  }

  public boolean equals(Object obj)
  {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    Subscription other = (Subscription)obj;
    if (this.id == null) {
      if (other.id != null)
        return false;
    } else if (!this.id.equals(other.id))
      return false;
    if (this.itemName == null) {
      if (other.itemName != null)
        return false;
    } else if (!this.itemName.equals(other.itemName)) {
      return false;
    }

    return true;
  }

  public String toString()
  {
    return "Subscription [id=" + this.id + ", itemName=" + this.itemName + 
      ", duration=" + this.duration + ", price=" + this.price + ", version=" + 
      this.version + "]";
  }
}