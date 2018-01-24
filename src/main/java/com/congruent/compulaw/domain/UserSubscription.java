/*package com.congruent.compulaw.domain;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;


*//**
 * The persistent class for the USER_SUBSCRIPTION database table.
 * 
 *//*

@Embeddable
@Table(name="USER_SUBSCRIPTION")
@Entity
@AssociationOverrides({
	@AssociationOverride(name = "pk.user", joinColumns = @JoinColumn(name = "USER_ID")),
	@AssociationOverride(name = "pk.subscription", joinColumns = @JoinColumn(name = "SUBSCRIPTION_ID"))})
public class UserSubscription implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private UserSubscriptionPK pk = new UserSubscriptionPK();
	
	private String approvedBy;
	private DateTime dateCreated;
	private DateTime dateApproved;
	private DateTime tellerDate;
	private DateTime dueDate;
	private boolean enabled = true;
	private String tellerNumber;
	private String amountPaid;
	private String bankName;
	private String itemName;
	private String subscriber;
	private Long transactionId;

	//private String tellerDateString;

	public UserSubscription() {
	}

	@EmbeddedId
	public UserSubscriptionPK getPk() {
		return this.pk;
	}

	public void setPk(UserSubscriptionPK pk) {
		this.pk = pk;
	}
	
	@Transient
	public User getUser() {
		return getPk().getUser();
	}

	public void setUser(User user) {
		getPk().setUser(user);
	}

	@Transient
	public Subscription getSubscription() {
		return getPk().getSubscription();
	}

	public void setSubscription(Subscription subscription) {
		getPk().setSubscription(subscription);
	}

	@Column(name="APPROVED_BY", length=25)
	public String getApprovedBy() {
		return approvedBy;
	}

	public void setApprovedBy(String approvedBy) {
		this.approvedBy = approvedBy;
	}

	@Column(name = "DATE_CREATED")
	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	@DateTimeFormat(iso = ISO.DATE)
	public DateTime getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(DateTime dateCreated) {
		this.dateCreated = dateCreated;
	}

	@Column(name = "DATE_APPROVED")
	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	@DateTimeFormat(iso = ISO.DATE)
	public DateTime getDateApproved() {
		return dateApproved;
	}

	public void setDateApproved(DateTime dateApproved) {
		this.dateApproved = dateApproved;
	}

	@Column(name = "DUE_DATE")
	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	@DateTimeFormat(iso = ISO.DATE)
	public DateTime getDueDate() {
		return dueDate;
	}

	public void setDueDate(DateTime dueDate) {
		this.dueDate = dueDate;
	}

	@Column(name="ENABLED")
	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	
	@NotNull
	@Column(name="TELLER", length=10)
	public String getTellerNumber() {
		return tellerNumber;
	}

	public void setTellerNumber(String tellerNumber) {
		this.tellerNumber = tellerNumber;
	}

	@NotNull
	@Column(name="BANK")
	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	@NotNull
	@Column(name="TELLERDATE")
	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	@DateTimeFormat(iso = ISO.DATE)
	public DateTime getTellerDate() {
		return tellerDate;
	}

	public void setTellerDate(DateTime tellerDate) {
		this.tellerDate = tellerDate;
	}
	
	@Transient
	public String getTellerDateString() {
		String tellerDateString = "";
		if (tellerDate != null)
			tellerDateString = org.joda.time.format.DateTimeFormat.forPattern(
					"yyyy-MM-dd").print(tellerDate);
		return tellerDateString;
	}

	@NotNull
	@Column(name="AMOUNT")
	public String getAmountPaid() {
		return amountPaid;
	}

	public void setAmountPaid(String amountPaid) {
		this.amountPaid = amountPaid;
	}

	@NotNull
	@Column(name="ITEM_NAME")
	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	@NotNull
	@Column(name="SUBSCRIBER")
	public String getSubscriber() {
		return subscriber;
	}

	public void setSubscriber(String subscriber) {
		this.subscriber = subscriber;
	}

	@NotNull
	@Column(name="TRANSACTION_ID")
	public Long getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(Long transactionId) {
		this.transactionId = transactionId;
	}

	@Override
	public String toString() {
		return "UserSubscription [getTellerNumber()=" + getTellerNumber()
				+ ", getBankName()=" + getBankName()
				+ ", getTellerDateString()=" + getTellerDateString()
				+ ", getAmountPaid()=" + getAmountPaid() + ", getItemName()="
				+ getItemName() + ", getSubscriber()=" + getSubscriber()
				+ ", getTransactionId()=" + getTransactionId() + "]";
	}

	
	

}*/